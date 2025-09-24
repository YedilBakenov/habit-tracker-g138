package ru.habbit.tracker.error;

public class ModelNotFoundException extends RuntimeException{

    public ModelNotFoundException(){
        super();
    }
    public ModelNotFoundException(String message){
        super(message);
    }
}
