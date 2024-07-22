package com.home.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

//    @Value("${twilio.phone.number}")
//    private String fromNumber;


    public void sendSms(String to, String body) {
            Twilio.init(accountSid, authToken);
            Message message= Message.creator(
                    new com.twilio.type.PhoneNumber(to),
                    new com.twilio.type.PhoneNumber("+13254200528"),body).create(); //your_twilio_phone_number
              System.out.println("SMS sent with SID:"+message.getSid());

        }
}