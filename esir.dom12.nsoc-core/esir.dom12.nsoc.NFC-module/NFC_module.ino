/**
* Author: Guillaume Le Floch
* Date: 11/12/12
* Version: 1.0
* FileName: NFC_module.c
*/

// This programm use an arduino mega and the 125Khz RFID module UART.
// The goal of this program is to get the id from a NFC tag, then the id is 
// sent throught the Serial bus to the 'Gestion d'accès' component.
//
// We are using an arduino mega because we need two serial bus, One for the
// NFC module and an other one to communicate with the other component.

/**
* Variable
*/

char id; // Id read from the NFC tag

/**
* The setup function is defined to set all the variables.
* This is the first function which is called when the arduino is powered on
*/
void setup(){
 
 // Init the serial 
 Serial.begin(9600);
 Serial1.begin(9600);
 }

/**
* The loop function is the function which is execute right after 
* the setup() function.
*/
void loop(){
  
  if(Serial.available()){           // Check if the serial connection is available   
    while(Serial.available())       // Loop on the Serial bus availibility 
    id = Serial.read();             // Copy the id into our variable
    Serial.write(id);
  }
}
