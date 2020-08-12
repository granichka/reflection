package local.nix.reflection.factory.impl;

import local.nix.reflection.annotation.PropertyKey;
import local.nix.reflection.data.AppProperties;
import local.nix.reflection.factory.ObjectFactory;
import local.nix.reflection.reader.PropertyReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;


public class AppPropertiesFactory implements ObjectFactory<AppProperties> {

    private static PropertyReader propertyReader;
    private String source;
    private static AppProperties instance;

            public AppPropertiesFactory(PropertyReader propertyReader, String source) {
                if(propertyReader != null && source != null) {
                    this.propertyReader = propertyReader;
                    this.source = source;
                } else {
                    throw new IllegalArgumentException("AppPropertiesFactory constructor must not have null parameters");
                }
            }

        public AppProperties getInstance() {

            try {
                instance = AppProperties.class.getConstructor().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

            setProperties(source);
                    return instance;

        }

        private static void setProperties(String source) {
            Map<String, String> properties = propertyReader.readProperties(source);
            Field[] publicFieldsOfAppPropertiesClass = AppProperties.class.getFields();


            for(Field currentField: publicFieldsOfAppPropertiesClass) {
                if(currentField.isAnnotationPresent(PropertyKey.class)) {
                        Class typeOfCurrentField = currentField.getType();
                        PropertyKey propertyKeyAnnotation = currentField.getAnnotation(PropertyKey.class);
                        String valueOfAnnotation = propertyKeyAnnotation.value();
                        if(properties.containsKey(valueOfAnnotation)) {
                                String valueOfCurrentField = properties.get(valueOfAnnotation);
                            try {
                                currentField.set(instance, convertStringToTypeOfField(valueOfCurrentField, typeOfCurrentField));
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (NoSuchMethodException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            }

                        }
                }
            }


        }

    private static <T> T convertStringToTypeOfField (String s, Class<T> type) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

                if (type.isPrimitive()) {
                    switch(type.getSimpleName()) {
                        case "byte":
                            return (T) Byte.class.getConstructor(String.class).newInstance(s);
                        case "short":
                            return  (T) Short.class.getConstructor(String.class).newInstance(s);

                        case "int":
                            return (T) Integer.class.getConstructor(String.class).newInstance(s);

                        case "long":
                            return (T) Long.class.getConstructor(String.class).newInstance(s);

                        case "float":
                            return (T) Float.class.getConstructor(String.class).newInstance(s);

                        case "double":
                            return (T) Double.class.getConstructor(String.class).newInstance(s);

                        case "boolean":
                            return (T) Boolean.class.getConstructor(String.class).newInstance(s);

                        case "char":
                            return (T) Character.class.getConstructor(String.class).newInstance(s);

                    }
            } else {
                switch(type.getSimpleName()) {
                    case "Byte":
                    case "Short":
                    case "Integer":
                    case "Long":
                    case "Float":
                    case "Double":
                    case "Boolean":
                    case "Character":
                    case "String":
                        return type.getConstructor(String.class).newInstance(s);


                }

            }
            return null;
    }
}
