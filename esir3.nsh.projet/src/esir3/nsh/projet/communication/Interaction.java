package esir3.nsh.projet.communication;

import esir3.nsh.projet.communication.*;

public class Interaction implements Communication {

    protected String address;
    protected String shutterState;
    protected String lightInState;
    protected String lightExtState;
    protected String weatherState;
    protected String tempreratureInState;
    protected String tempreratureExtState;


    public Interaction(String address, int port) {
        this.address = address + ":" + port;

        createAllFactories();
    }


    public double getTemperatureInt() {

        return intTempreratureDatas.getTemperature();
    }

    public double getTemperatureExt() {
        return extTempreratureDatas.getTemperature();
    }

    public boolean getWheater() {
        return weatherDatas.isSunny();
    }

    public int getShutterState() {
        return shutterDatas.getState();
    }

    public int getLightBrightnessInt() {
        return intLightDatas.getBrightnessValue();
    }

    public int getLightBrightnessExt() {
        return extLightDatas.getBrightnessValue();
    }

    public int getLightLevel() {
        return percentageLightDatas.getLevel();
    }

    public void setLightLevel(int level) {
        percentageLightDatas.setLevel(level);
    }

    public void setShutterUp() {
        shutterDatas.pullUp();
    }

    public void setShutterDown() {
        shutterDatas.pullDown();
    }

    public void setShutterIntermediate() {
        shutterDatas.setInterediate();
    }

    public String getServices() {
        return serviceExplorerDatas.getAvailableServices();
    }


    public void createAllFactories() {

        Factory shutterFactory = new Factory(this.address + "/Shutter", ShutterWs.class);
        shutterDatas = (ShutterWs) shutterFactory.create();

        Factory intTempreratureFactory = new Factory(this.address + "/IntTempProbe", TemperatureProbeWs.class);
        intTempreratureDatas = (TemperatureProbeWs) intTempreratureFactory.create();

        Factory extTempreratureFactory = new Factory(this.address + "/ExtTempProbe", TemperatureProbeWs.class);
        extTempreratureDatas = (TemperatureProbeWs) extTempreratureFactory.create();

        Factory intLightFactory = new Factory(this.address + "/IntLightProbe", LightProbeWs.class);
        intLightDatas = (LightProbeWs) intLightFactory.create();

        Factory extLightFactory = new Factory(this.address + "/ExtLightProbe", LightProbeWs.class);
        extLightDatas = (LightProbeWs) extLightFactory.create();

        Factory percentageLightFactory = new Factory(this.address + "/Light", LightWs.class);
        percentageLightDatas = (LightWs) percentageLightFactory.create();


        Factory weatherFactory = new Factory(this.address + "/ExtWeatherProbe", WeatherProbeWs.class);
        weatherDatas = (WeatherProbeWs) weatherFactory.create();

        Factory serviceExplorerFactory = new Factory(this.address + "/WsServiceExplorer", WsServiceExplorer.class);
        serviceExplorerDatas = (WsServiceExplorer) serviceExplorerFactory.create();


    }


}
