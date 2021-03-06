package com.dhr.quad;

public interface Protocol {  
    
    // protocol manager handles each received byte  
    void onReceive(byte b);  
      
    // protocol manager handles broken stream  
    void onStreamClosed();  
}  