package com.neotys.NLAPIClient;

import com.neotys.rest.dataexchange.client.DataExchangeAPIClient;
import com.neotys.rest.dataexchange.client.DataExchangeAPIClientFactory;
import com.neotys.rest.dataexchange.model.ContextBuilder;
import com.neotys.rest.dataexchange.model.Entry;
import com.neotys.rest.dataexchange.model.EntryBuilder;
import com.neotys.rest.error.NeotysAPIException;
import com.neotys.webpagetest.WPT.WptResponse;
import org.apache.olingo.odata2.api.exception.ODataException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hrexed on 01/06/18.
 */
public class NLClient {
    private DataExchangeAPIClient client;
    private ContextBuilder Context;
    private WptResponse content ;
    private String NLUrl;
    private String NLApiKey;

    public NLClient(WptResponse content, String NLUrl, String NLApiKey) throws URISyntaxException, GeneralSecurityException, ODataException, NeotysAPIException, IOException {
        this.content = content;
        this.NLUrl = NLUrl;
        this.NLApiKey = NLApiKey;
        String[] TestingContext;

        if(this.content!=null)
        {
            TestingContext = GetLocationAndBrowser(content.data.location);

            Context = new ContextBuilder();
            Context.hardware(TestingContext[1]).location(TestingContext[0]).software(TestingContext[1])

                    .script("WebPageTest" + content.data.completed);

            client = DataExchangeAPIClientFactory.newClient(NLUrl, Context.build(), NLApiKey);


        }
    }
    public void SendData() throws GeneralSecurityException, IOException, NeotysAPIException, ParseException, URISyntaxException {
        ParseWPTResponse();
    }

    private Entry GenerateEntry(String URL, String SubCategory, String MetricName,  double value, String unit, long  ValueDate,String url) throws GeneralSecurityException, IOException, URISyntaxException, NeotysAPIException, ParseException
    {

        EntryBuilder entry=new EntryBuilder(Arrays.asList("WPT", URL, SubCategory,MetricName), ValueDate);
        entry.unit(unit);
        entry.url(url);
        entry.value(value);

        return entry.build();
    }

    private void ParseWPTResponse() throws URISyntaxException, GeneralSecurityException, ParseException, NeotysAPIException, IOException {
        final List<Entry> entriesToSend = new ArrayList<>();

        //----response time-----
        entriesToSend.add(GenerateEntry(content.data.url,"ResponseTime","Loadtime",(double)content.data.average.loadTime,"ms",content.data.completed,content.data.summary));
        entriesToSend.add(GenerateEntry(content.data.url,"ResponseTime","TTFB",content.data.average.TTFB,"ms",content.data.completed,content.data.summary));
        entriesToSend.add(GenerateEntry(content.data.url,"ResponseTime","render",content.data.average.render ,"ms",content.data.completed,content.data.summary));
        entriesToSend.add(GenerateEntry(content.data.url,"ResponseTime","fullyLoaded",content.data.average.fullyLoaded ,"ms",content.data.completed,content.data.summary));
        entriesToSend.add(GenerateEntry(content.data.url,"ResponseTime","docTime",content.data.average.docTime ,"ms",content.data.completed,content.data.summary));
        entriesToSend.add(GenerateEntry(content.data.url,"ResponseTime","firstLayout",content.data.average.firstLayout ,"ms",content.data.completed,content.data.summary));
        entriesToSend.add(GenerateEntry(content.data.url,"ResponseTime","domComplete",content.data.average.domComplete ,"ms",content.data.completed,content.data.summary));
        entriesToSend.add(GenerateEntry(content.data.url,"ResponseTime","domTime",content.data.average.domTime ,"ms",content.data.completed,content.data.summary));
        entriesToSend.add(GenerateEntry(content.data.url,"ResponseTime","visualComplete",content.data.average.visualComplete ,"ms",content.data.completed,content.data.summary));
        entriesToSend.add(GenerateEntry(content.data.url,"ResponseTime","domInteractive",content.data.average.domInteractive ,"ms",content.data.completed,content.data.summary));

        //---network Stats---
        entriesToSend.add(GenerateEntry(content.data.url,"Network","bytesIn",content.data.average.bytesIn,"bytes",content.data.completed,content.data.summary));
        entriesToSend.add(GenerateEntry(content.data.url,"Network","bytesOut",content.data.average.bytesOut,"bytes",content.data.completed,content.data.summary));
        entriesToSend.add(GenerateEntry(content.data.url,"Network","bytesInDoc",content.data.average.bytesInDoc ,"bytes",content.data.completed,content.data.summary));
        entriesToSend.add(GenerateEntry(content.data.url,"Network","bytesOutDoc",content.data.average.bytesOutDoc ,"bytes",content.data.completed,content.data.summary));


        //---score---
        entriesToSend.add(GenerateEntry(content.data.url,"Score","score_cdn",content.data.average.score_cdn ,"score",content.data.completed,content.data.summary));
        entriesToSend.add(GenerateEntry(content.data.url,"Score","score_progressive_jpeg",content.data.average.score_progressive_jpeg ,"score",content.data.completed,content.data.summary));
        entriesToSend.add(GenerateEntry(content.data.url,"Score","score_etags",content.data.average.score_etags ,"score",content.data.completed,content.data.summary));
        entriesToSend.add(GenerateEntry(content.data.url,"Score","score_cache",content.data.average.score_cache ,"score",content.data.completed,content.data.summary));
        entriesToSend.add(GenerateEntry(content.data.url,"Score","score_keepalive",content.data.average.score_keepalive ,"score",content.data.completed,content.data.summary));
        entriesToSend.add(GenerateEntry(content.data.url,"Score","score_compress",content.data.average.score_compress ,"score",content.data.completed,content.data.summary));
        entriesToSend.add(GenerateEntry(content.data.url,"Score","SpeedIndex",content.data.average.SpeedIndex ,"score",content.data.completed,content.data.summary));


        //---Stats---
        entriesToSend.add(GenerateEntry(content.data.url,"Stats","requests",content.data.average.requests ,"nb",content.data.completed,content.data.summary));
        entriesToSend.add(GenerateEntry(content.data.url,"Stats","requestsDoc",content.data.average.requestsDoc ,"nb",content.data.completed,content.data.summary));
        entriesToSend.add(GenerateEntry(content.data.url,"Stats","requestsFull",content.data.average.requestsFull ,"nb",content.data.completed,content.data.summary));
        entriesToSend.add(GenerateEntry(content.data.url,"Stats","responses_404",content.data.average.responses_404 ,"nb",content.data.completed,content.data.summary));
        entriesToSend.add(GenerateEntry(content.data.url,"Stats","responses_200",content.data.average.responses_200 ,"nb",content.data.completed,content.data.summary));
        entriesToSend.add(GenerateEntry(content.data.url,"Stats","connections",content.data.average.connections ,"nb",content.data.completed,content.data.summary));
        entriesToSend.add(GenerateEntry(content.data.url,"Stats","image_total",content.data.average.image_total ,"nb",content.data.completed,content.data.summary));
        entriesToSend.add(GenerateEntry(content.data.url,"Stats","domElements",content.data.average.domElements ,"nb",content.data.completed,content.data.summary));

        client.addEntries(entriesToSend);
    }

    public String[] GetLocationAndBrowser(String location)
    {
        String[] result=location.split(":");

        return result;
    }
}
