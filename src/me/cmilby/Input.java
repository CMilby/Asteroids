package me.cmilby;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Input implements KeyListener, MouseListener, MouseMotionListener {

	public static final int MAX_KEYS = 512;
	public static final int MAX_BUTTONS = 64;
	
	public static final int KEY_ENTER = '\n';
	public static final int KEY_BACKSPACE = '\b';
	public static final int KEY_TAB = '\t';
	public static final int KEY_CANCEL = 0x03;
	public static final int KEY_CLEAR = 0x0C;
	public static final int KEY_SHIFT = 0x10;
	public static final int KEY_CONTROL = 0x11;
	public static final int KEY_ALT = 0x12;
	public static final int KEY_PAUSE = 0x13;
	public static final int KEY_CAPS_LOCK = 0x14;
	public static final int KEY_ESCAPE = 0x1B;
	public static final int KEY_SPACE = 0x20;
	public static final int KEY_PAGE_UP = 0x21;
	public static final int KEY_PAGE_DOWN = 0x22;
	public static final int KEY_END = 0x23;
	public static final int KEY_HOME = 0x24;
	
	public static final int KEY_LEFT = 0x25;
	public static final int KEY_UP = 0x26;
	public static final int KEY_RIGHT = 0x27;
	public static final int KEY_DOWN = 0x28;
	
	public static final int KEY_COMMA = 0x2C;
	public static final int KEY_MINUS = 0x2D;
	public static final int KEY_PERIOD = 0x2E;
	public static final int KEY_SLASH = 0x2F;
	
	public static final int KEY_0 = 0x30;
	public static final int KEY_1 = 0x31;
	public static final int KEY_2 = 0x32;
	public static final int KEY_3 = 0x33;
	public static final int KEY_4 = 0x34;
	public static final int KEY_5 = 0x35;
	public static final int KEY_6 = 0x36;
	public static final int KEY_7 = 0x37;
	public static final int KEY_8 = 0x38;
	public static final int KEY_9 = 0x39;
	
	public static final int KEY_SEMICOLON = 0x3B;
	public static final int KEY_EQUALS = 0x3D;
	
	public static final int KEY_A = 0x41;
	public static final int KEY_B = 0x42;
	public static final int KEY_C = 0x43;
	public static final int KEY_D = 0x44;
	public static final int KEY_E = 0x45;
	public static final int KEY_F = 0x46;
	public static final int KEY_G = 0x47;
	public static final int KEY_H = 0x48;
	public static final int KEY_I = 0x49;
	public static final int KEY_J = 0x4A;
	public static final int KEY_K = 0x4B;
	public static final int KEY_L = 0x4C;
	public static final int KEY_M = 0x4D;
	public static final int KEY_N = 0x4E;
	public static final int KEY_O = 0x4F;
	public static final int KEY_P = 0x50;
	public static final int KEY_Q = 0x51;
	public static final int KEY_R = 0x52;
	public static final int KEY_S = 0x53;
	public static final int KEY_T = 0x54;
	public static final int KEY_U = 0x55;
	public static final int KEY_V = 0x56;
	public static final int KEY_W = 0x57;
	public static final int KEY_X = 0x58;
	public static final int KEY_Y = 0x59;
	public static final int KEY_Z = 0x5A;
	
	public static final int KEY_OPEN_BRACKET = 0x5B;
	public static final int KEY_BACKSLASH = 0x5C;
	public static final int KEY_CLOSE_BRACKET = 0x5D;
	
	public static final int KEY_KP_0 = 0x60;
	public static final int KEY_KP_1 = 0x61;
	public static final int KEY_KP_2 = 0x62;
	public static final int KEY_KP_3 = 0x63;
	public static final int KEY_KP_4 = 0x64;
	public static final int KEY_KP_5 = 0x65;
	public static final int KEY_KP_6 = 0x66;
	public static final int KEY_KP_7 = 0x67;
	public static final int KEY_KP_8 = 0x68;
	public static final int KEY_KP_9 = 0x69;
	
	public static final int KEY_KP_UP = 0xE0;
	public static final int KEY_KP_DOWN = 0xE1;
	public static final int KEY_KP_LEFT = 0xE2;
	public static final int KEY_KP_RIGHT = 0xE3;
	
	public static final int KEY_MULTIPLY = 0x6A;
	public static final int KEY_ADD = 0x6B;
	public static final int KEY_SUBTRACT = 0x6D;
	public static final int KEY_DECIMAL = 0x6E;
	public static final int KEY_DIVIDE = 0x6F;
	public static final int KEY_DELETE = 0x7F;
	public static final int KEY_NUM_LOCK = 0x90;
	public static final int KEY_SCROLL_LOCK = 0x91;
	
	public static final int KEY_F1 = 0x70;
	public static final int KEY_F2 = 0x71;
	public static final int KEY_F3 = 0x72;
	public static final int KEY_F4 = 0x73;
	public static final int KEY_F5 = 0x74;
	public static final int KEY_F6 = 0x75;
	public static final int KEY_F7 = 0x76;
	public static final int KEY_F8 = 0x77;
	public static final int KEY_F9 = 0x78;
	public static final int KEY_F10 = 0x79;
	public static final int KEY_F11 = 0x7A;
	public static final int KEY_F12 = 0x7B;
	
	public static final int KEY_PRINTSCREEN = 0x9A;
	public static final int KEY_INSERT = 0x9B;
	public static final int KEY_HELP = 0x9C;
	public static final int KEY_META = 0x9D;
	
	public static final int KEY_BACK_QUOTE = 0xC0;
	public static final int KEY_QUOTE = 0xDE;
	
	private static Input instance = new Input();

	private boolean[] keyInput;
	private boolean[] mouseInput;
	
	private Vector2f mousePosition;
	
	private Input() {
		mousePosition = new Vector2f();
		
		keyInput = new boolean[MAX_KEYS];
		mouseInput = new boolean[MAX_BUTTONS];
		
		for (int i = 0; i < MAX_KEYS; i++)
			keyInput[i] = false;
		for (int i = 0; i < MAX_BUTTONS; i++)
			mouseInput[i] = false;
	}
	
	public boolean isKeyDown(int keyCode) {
		return keyInput[keyCode];
	}
	
	public boolean isKeyUp(int keyCode) {
		return !keyInput[keyCode];
	}
	
	public boolean isButtonDown(int button) {
		return mouseInput[button];
	}
	
	public boolean isButtonUp(int button) {
		return !mouseInput[button];
	}
	
	public Vector2f getMousePosition() {
		return mousePosition;
	}
	
	public static Input getInstance() {
		return instance;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mousePosition.setX(e.getX());
		mousePosition.setY(e.getY());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseInput[e.getButton()] = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseInput[e.getButton()] = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keyInput[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keyInput[e.getKeyCode()] = false;
	}
}
