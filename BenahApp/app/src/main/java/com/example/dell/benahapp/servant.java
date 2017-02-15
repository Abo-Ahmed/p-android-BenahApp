package com.example.dell.benahapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

public class servant {

    public static AppCompatActivity app;

    public static boolean safe = false;
    public static void init_modules(AppCompatActivity ap)
    {
        app = ap;
        frontCameraController.setContext(app);
        Movement_sensor.start_Accelerator(app);

        if(! data_factory.loadLast())
            data_factory.showFileChooser(app);

    }

    public static boolean is_similar()
    {
        boolean tmp = frontCameraController.similar;
        frontCameraController.similar = false;
        return tmp;
    }

    public static void save_data()
    {
        data_factory.saveAttendence(data_factory.getStringData());
    }

    public static void recordAttendence(){

        frontCameraController.takePicture();

    }

    public static void check_bad_student(String data)
    {
        if(  is_similar() && Movement_sensor.is_stable())
        {
            data_factory.pluserismData.add(data);
        }
    }

    public static void check_Activity_results(int requestCode,int resultCode ,Intent data)
    {
        QR_factory.process_result(requestCode, resultCode , data);
        data_factory.check_file(requestCode, resultCode , data);
        Movement_sensor.resetState();
    }

    public static void pause_Activity()
    {
        save_data();
        Movement_sensor.pause_Accelerometer();
        frontCameraController.releaseCamera();
    }

    public static void removeAttend(String data)
    {
        data_factory.removeAttendence(data);
    }

    public static void resume_Activity()
    {
        Movement_sensor.resume_Accelerometer();
    }

}
