package com.home.Controller;

import com.home.Service.BucketService;
import com.home.Service.PDFService;
import com.home.Service.TwilioService;
import com.home.entity.AppUser;
import com.home.entity.Booking;
import com.home.entity.Property;
import com.home.repository.BookingRepository;
import com.home.repository.PropertyRepository;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.DocFlavor;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {
    private BookingRepository bookingRepository;
    private PropertyRepository propertyRepository;
    private TwilioService twilioService;
    private BucketService bucketService;
    private PDFService pdfService;
    private BookingController(BookingRepository bookingRepository, PropertyRepository propertyRepository, TwilioService twilioService, BucketService bucketService, PDFService pdfService) {
        this.bookingRepository = bookingRepository;
        this.propertyRepository = propertyRepository;
        this.twilioService = twilioService;
        this.bucketService = bucketService;
        this.pdfService = pdfService;
    }
    @PostMapping
    public ResponseEntity<Booking>createBooking(
            @RequestBody Booking booking,
            @AuthenticationPrincipal AppUser User,
            @RequestParam long propertyId
    ){
        Property property = propertyRepository.findById(propertyId).get();
        Double nightlyPrice = property.getNightlyPrice();
        int totalPrice = (int) (nightlyPrice*booking.getTotalNights());
//        double priceWithtax = totalPrice*(18/100);
        booking.setTotalPrice(totalPrice);
        booking.setAppUser(User);
        booking.setProperty(property);
        Booking savedbooking = bookingRepository.save(booking);
        String filePath = pdfService.generateBookingDetailsPdf(savedbooking);

        try {
            MultipartFile fileMultipart = BookingController.convert(filePath);
            String fileUploadedUrl = bucketService.uploadFile(fileMultipart, "b316815m");
            System.out.println(fileUploadedUrl);
            sendMessage(fileUploadedUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(savedbooking, HttpStatus.CREATED);
    }

    public void sendMessage(String url) {
        twilioService.sendSms("+919794947238","Salary Credited to Your Account"+url);
    }


//@PostMapping("/generatePdf")
//        public void generatePdf(){
//         pdfService.generateBookingDetailsPdf();
//}


    public static MultipartFile convert (String filePath) throws IOException{
        File file = new File(filePath);
        byte[] fileContent = Files.readAllBytes(file.toPath());
        Resource resource = new ByteArrayResource(fileContent);
        MultipartFile multiPartFile = new MultipartFile(){
           @Override
           public String getName(){
               return file.getName();
           }
           @Override
            public String getOriginalFilename(){
               return file.getName();
           }
           @Override
            public String getContentType(){
               return null;
           }
            @Override
            public boolean isEmpty(){
               return fileContent.length ==0;
            }
            @Override
            public long getSize(){
               return fileContent.length;
            }
            @Override
            public byte[] getBytes() throws IOException{
               return fileContent;
           }
           @Override
            public InputStream getInputStream() throws IOException{
               return resource.getInputStream();
           }
           @Override
            public void transferTo(File dest) throws IOException,IllegalStateException{
               Files.write(dest.toPath(), fileContent);
           }
        };
        return multiPartFile;
     }

}
