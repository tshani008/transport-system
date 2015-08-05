/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package transportsystem;

/**
 *
 * @author Theja
 */
public class MessageUtil {
    private String message;
    
    
    
	/**
	 * Constructor
	 * 
	 * @param message to be printed
	 */
	public MessageUtil(String message) {
		this.message = message;
	}

	/**
	 * prints the message
	 * @return
	 */
	public String printMessage() {
		System.out.println(message);
		return message;
	}

	/**
	 * Add message
	 * @return
	 */
	public String salutationMessage() {
		message = "Read This Message :" + message;
		System.out.println(message);
		return message;
	}
    
}
