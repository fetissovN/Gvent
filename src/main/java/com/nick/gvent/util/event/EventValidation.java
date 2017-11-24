package com.nick.gvent.util.event;


import com.nick.gvent.dto.EventDTO;
import com.nick.gvent.entity.Event;
import com.nick.gvent.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventValidation {

    private static Logger LOGGER;


    private static EventValidation  singleton = new EventValidation();

    private EventValidation () {
    }

    public static EventValidation getInstance(){
        LOGGER = LoggerFactory.getLogger(singleton.getClass());
        return singleton;
    }

    public static boolean validate(EventDTO event){
        String name = event.getName();
        String desc = event.getDescription();
        String lat = event.getLatitude();
        String lng = event.getLongitude();

        if (StringUtils.isNotBlank(name) ||
                StringUtils.isNotBlank(desc)){
            if (StringUtils.isNotBlank(lat) ||
                StringUtils.isNotBlank(lng)){
                if (latValid(lat) || lngValid(lng)){
                    return true;
                }
            }
        }
        LOGGER.warn("Wrong event {}",event);
        return false;

    }

    private static boolean latValid(String lat){
        if (lat.contains(".")){
            String latArr[] = lat.split("\\.");
            if (!commonCheck(latArr)){
                return false;
            }
            if (Long.parseLong(latArr[0]) > 90 || Long.parseLong(latArr[0]) < -90){
                return false;
            }
            return true;
        }
        return false;
    }

    private static boolean lngValid(String lng){
        if (lng.contains(".")){
            String latArr[] = lng.split("\\.");
            if (!commonCheck(latArr)){
                return false;
            }if (Long.parseLong(latArr[0]) > 180 || Long.parseLong(latArr[0]) < -180){
                return false;
            }
            return true;
        }
        return false;
    }

    private static boolean commonCheck(String[] latArr){
        if (latArr.length != 2){
            return false;
        }
        if (latArr[0].length() > 2 || latArr[0].length() < 1){
            return false;
        }
        if (latArr[1].length() < 1 || latArr[1].length() > 20){
            return false;
        }
        if (Long.parseLong(latArr[1]) < 0){
            return false;
        }
        return true;
    }

}
