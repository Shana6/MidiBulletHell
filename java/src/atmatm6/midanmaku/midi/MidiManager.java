package atmatm6.midanmaku.midi;

import javax.sound.midi.*;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

import static java.lang.System.out;

public class MidiManager {
    private static String prefnode = "/atmatm6/midanmaku";
    private MidiDevice selectedDevice;
    private Soundbank sb;
    private Preferences prefs;
    private Synthesizer synthesizer;
    private Receiver synthrec;
    private Sequencer sequencer;
    private Transmitter synthtrans;

    public MidiManager() throws Exception {
        init();
    }

    private void init() throws Exception{
        sequencer = MidiSystem.getSequencer();
        prefs = Preferences.userRoot().node(prefnode);
        String device = prefs.get("device",null);
        if (device == null) {
            setSb(MidiSystem.getSoundbank(getClass().getResourceAsStream("/genusrGS.sf2")));
            synthesizer = MidiSystem.getSynthesizer();
            synthtrans = sequencer.getTransmitter();
            synthrec = synthesizer.getReceiver();
            synthtrans.setReceiver(synthrec);
            selectedDevice = null;
        } else {
            final boolean[] nameFound = {false};
            MidiManager.getInputs().forEach(devic -> {
                if (devic.getDeviceInfo().getName().equals(device)){
                    selectedDevice = devic;
                    nameFound[0] = true;
                }
            });
            if (!nameFound[0]){
                selectedDevice = null;
                sb = MidiSystem.getSoundbank(getClass().getResourceAsStream("/genusrGS.sf2"));
            }
        }
    }
    public void useSynthesizer(){
        prefs.put("device", null);
    }
    public void setDevice(MidiDevice device){
        selectedDevice = device;

        prefs.put("device",device.getDeviceInfo().getName());
    }
    public static ArrayList<MidiDevice> getInputs(){
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        ArrayList<MidiDevice> transDevices = new ArrayList<>();
        for (MidiDevice.Info info: infos) {
            MidiDevice device = null;
            try {
                device = MidiSystem.getMidiDevice(info);
                List<Transmitter> t = device.getTransmitters();
                assert t != null;
                if (1 < t.size()){
                    transDevices.add(device);
                    out.println(info.getName());
                }
            } catch (Exception ignored) {}
        }
        return transDevices;
    }
    //to be used
    public static ArrayList<MidiDevice> getOutputs(){
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        ArrayList<MidiDevice> recDevices = new ArrayList<>();
        for (MidiDevice.Info info: infos) {
            MidiDevice device = null;
            try {
                device = MidiSystem.getMidiDevice(info);
                List<Receiver> r = device.getReceivers();
                if (r.size() > 1){
                    recDevices.add(device);
                    out.println(info.getName());
                }
            } catch (Exception ignored) {}
        }
        return recDevices;
    }

    private Soundbank getSb() {
        return sb;
    }

    private void setSb(Soundbank sb) {
        this.sb = sb;
    }
}
