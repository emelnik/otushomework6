package exceptions;

import data.DriverData;

import java.util.Locale;

public class BrowserNotSupported extends Exception{

    public BrowserNotSupported(DriverData browserType){
        super(String.format("Browser %s is not supported", browserType.name().toLowerCase(Locale.ROOT)));
    }

}
