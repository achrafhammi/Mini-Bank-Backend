package com.achraf.minibankbackend.utils;

import java.lang.reflect.Field;

public class ModelUpdater {

    public static <T> T updateModel(T existingModel, T updatedModel) {
        Class<?> modelClass = existingModel.getClass();
        Field[] fields = modelClass.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            try {
                Object updatedValue = field.get(updatedModel);
                if (updatedValue != null) {
                    field.set(existingModel, updatedValue);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return existingModel;
    }
}
