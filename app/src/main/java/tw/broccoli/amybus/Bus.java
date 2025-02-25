package tw.broccoli.amybus;

/**
 * Created by broccoli on 15/9/24.
 */
public class Bus {
    private String bus_number = null;
    private String bus_rid = null;
    private String bus_direct_param = null;
    private String bus_direct_text = null;
    private String bus_onbus = null;
    private Alarm bus_alarm = null;

    public Bus(String bus_number, String bus_rid, String bus_direct_param, String bus_direct_text, String bus_onbus, Alarm bus_alarm) {
        this.bus_number = bus_number;
        this.bus_rid = bus_rid;
        this.bus_direct_param = bus_direct_param;
        this.bus_direct_text = bus_direct_text;
        this.bus_onbus = bus_onbus;
        if(bus_alarm!=null) this.bus_alarm = bus_alarm;
    }

    public Bus(String bus_number, String bus_rid, String bus_direct_param, String bus_direct_text, String bus_onbus, String alarmString) {
        this.bus_number = bus_number;
        this.bus_rid = bus_rid;
        this.bus_direct_param = bus_direct_param;
        this.bus_direct_text = bus_direct_text;
        this.bus_onbus = bus_onbus;
        this.bus_alarm = Alarm.getAlarm(alarmString);
    }

    public boolean isComplete(){
        return bus_number!=null && bus_rid!=null && bus_direct_param !=null && bus_direct_text !=null && bus_onbus!=null;
    }

    public void setNumber(String bus_number) {
        this.bus_number = bus_number;
    }

    public void setRid(String bus_rid) {
        this.bus_rid = bus_rid;
    }

    public void setDirectParam(String bus_direct_param) {
        this.bus_direct_param = bus_direct_param;
    }

    public void setDirectText(String bus_direct_text) {
        this.bus_direct_text = bus_direct_text;
    }

    public void setOnBus(String bus_onbus) {
        this.bus_onbus = bus_onbus;
    }

    public void setAlarm(Alarm bus_alarm) {
        this.bus_alarm = bus_alarm;
    }

    public String getNumber() {
        return bus_number;
    }

    public String getRid() {
        return bus_rid;
    }

    public String getDirectParam() {
        return bus_direct_param;
    }

    public String getDirectText() {
        return bus_direct_text;
    }

    public String getOnBus() {
        return bus_onbus;
    }

    public Alarm getAlarm() {
        return bus_alarm;
    }

    public static Bus getBus(String widgetListText){
        String bus = widgetListText.substring(0, widgetListText.indexOf(" - "));
        String direct_text = widgetListText.substring(widgetListText.indexOf("往 "), widgetListText.indexOf(" ", widgetListText.indexOf("往 ") + 2));
//        String time = widgetListText.substring(widgetListText.lastIndexOf(" ", widgetListText.indexOf(" 到 ") - 1) + 1, widgetListText.indexOf(" 到 "));
        String onbus = widgetListText.substring(widgetListText.indexOf(" 到 ")+3);
        return new Bus(bus, "", "", direct_text, onbus, "");
    }
}
