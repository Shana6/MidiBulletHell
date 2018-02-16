package atmatm6.midanmaku;

import atmatm6.midanmaku.game.Board;
import atmatm6.midanmaku.midi.MidiManager;
import atmatm6.midanmaku.midi.Selector;

import java.awt.EventQueue;
import javax.swing.JFrame;


public class MidiDanmaku extends JFrame {
    public static int[] size = {500,500};
    private Selector selector;
    private MidiManager mm;

    public MidiDanmaku() throws Exception {
        initMIDI();
//      initUI();
    }

    private void initMIDI() throws Exception {
        mm = new MidiManager();
        selector = new Selector(mm);
    }

    private void initUI() {
        add(new Board());
        setSize(size[0], size[1]);
        setTitle("Midi Danmaku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) throws Exception{
        EventQueue.invokeLater(() -> {
            try {
                MidiDanmaku ex = new MidiDanmaku();
            } catch (Exception e) {
                e.printStackTrace();
            }
//          ex.setVisible(true);
        });
    }
}