package com.corona.coronavirus_tracker.services;

import com.corona.coronavirus_tracker.models.locationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service //telling spring about this service
public class CoronavirusDataService {
    public static String virus_url="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private  List<locationStats>  loc=new ArrayList<>();

    public List<locationStats> getLoc() {
        return loc;
    }

    @PostConstruct  //after creating an instance of class , they are asking to execute this function
    @Scheduled(cron = "* * 1 * * *") //for running regularly to get updated data

    public void  fetchVirusData() throws IOException, InterruptedException {
        List<locationStats> loc1=new ArrayList<>();
        HttpClient client=HttpClient.newHttpClient();
        HttpRequest request= HttpRequest.newBuilder()
                .uri(URI.create(virus_url))
                .build();
        HttpResponse<String> httpResponse= client.send(request , HttpResponse.BodyHandlers.ofString());
        //System.out.println(httpResponse.body());
        StringReader csvBodyReader =new StringReader(httpResponse.body());
        //Reader in = new FileReader("path/to/file.csv");
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {
            locationStats l=new locationStats();

            l.setState(record.get("Province/State"));
            l.setCountry(record.get("Country/Region"));
            l.setLatestTotalCases(Integer.parseInt(record.get(record.size()-1)));
            int c=Integer.parseInt(record.get(record.size()-1))-Integer.parseInt(record.get(record.size()-2));
            l.setChange(c);
            //System.out.println(l);
            loc1.add(l);
            //String name = record.get("Name");
        }this.loc=loc1;

    }
}
