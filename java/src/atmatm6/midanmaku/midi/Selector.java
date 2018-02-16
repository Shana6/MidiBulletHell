package atmatm6.midanmaku.midi;

import atmatm6.midanmaku.misc.ComboItem;

import javax.sound.midi.MidiDevice;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Selector extends JFrame{
    private JComboBox<ComboItem> cbox;

    public Selector(MidiManager mm) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Manage MIDI");
        cbox = new JComboBox<>();
        Box box = Box.createHorizontalBox();
        box.add(new Label("Devices"));
        box.add(cbox);
        add(box, BorderLayout.CENTER);
        ArrayList<MidiDevice> devices = MidiManager.getInputs();
        devices.forEach(device -> {
            cbox.addItem(new ComboItem(device.getDeviceInfo().getName(),device));
        });
        cbox.addItemListener(ie -> {
            ComboItem ci = (ComboItem) ie.getItem();
            mm.setDevice((MidiDevice) ci.getValue());
        });
    }

}
