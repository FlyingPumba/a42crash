package com.a42crash.iarcuschin.a42crash;

import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mate.IMATEServiceInterface;
import org.mate.IRepresentationLayerInterface;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RepLayer {

    private InternalHandler handler;
    private IMATEServiceInterface mateService;

    @Before
    public void setup() {
        String activityToBeTested = "com.a42crash.iarcuschin.a42crash.MainActivity";
        Class activityClass;
        try {
            activityClass = Class.forName(activityToBeTested);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        instrumentation.setInTouchMode(true);

        final String targetPackage = instrumentation.getTargetContext().getPackageName();
        Intent startIntent = new Intent(Intent.ACTION_MAIN);
        startIntent.setClassName(targetPackage, activityClass.getName());
        startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        instrumentation.startActivitySync(startIntent);
        instrumentation.waitForIdleSync();
    }

    @Test
    public void test() {
        Looper.prepare();
        handler = new InternalHandler();

        // interface, implemented service-side
        ServiceConnection connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mateService = IMATEServiceInterface.Stub.asInterface(service);

                IRepresentationLayerInterface.Stub representationLayerListener = new IRepresentationLayerInterface.Stub() {
                    @Override
                    public void getAvailableActions() throws RemoteException {
                        /*
                          This is called by the MATE service regularly to fetch ne available actions.
                          Note that IPC calls are dispatched through a thread
                          pool running in each process, so the code executing here will
                          NOT be running in our main thread like most other things -- so,
                          to update the UI (and use the Espresso API), we need to use a Handler to hop over there.
                         */
                        Log.i("MATE_REP_LAYER",  "Command received: getAvailableActions()");
                        handler.handleMessage(handler.obtainMessage(GET_AVAILABLE_ACTIONS, 0, 0));
                    }
                };

                try {
                    mateService.registerRepresentationLayer(representationLayerListener);
                } catch (RemoteException e) {
                    Log.i("MATE_REP_LAYER",  e.toString());
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.i("MATE_REP_LAYER",  "Representation layer disconnected from MATE Service");
            }
        };

        Intent intent = new Intent();
        intent.setClassName("org.mate", "org.mate.MATEService");

        final Context context = InstrumentationRegistry.getInstrumentation().getContext();
        context.bindService(intent, connection, Context.BIND_AUTO_CREATE);

        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static final int GET_AVAILABLE_ACTIONS = 1;

    private static class InternalHandler extends Handler {

        InternalHandler() {
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GET_AVAILABLE_ACTIONS:
                    Log.i("MATE_REP_LAYER",  "Handler processing command: getAvailableActions()");
                    // Toast.makeText(context, "Handler processing command: getAvailableActions()", Toast.LENGTH_LONG).show();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }
}
