package com.My.OsangProject;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;

public class exit extends Application {

private List<Activity> activityList = new LinkedList<Activity>(); 
private static exit instance;

            exit()
            {
            }
             //����ģʽ�л�ȡΨһ��MyApplicationʵ�� 
             public static exit getInstance()
             {
                            if(null == instance)
                          {
                             instance = new exit();
                          }
                 return instance;             
             }
             //���Activity��������
             public void addActivity(Activity activity)
             {
                            activityList.add(activity);
             }
             //��������Activity��finish
             public void exit()
             {
                          for(Activity activity:activityList)
                         {
                           activity.finish();
                         }
                           System.exit(0);
            }
}