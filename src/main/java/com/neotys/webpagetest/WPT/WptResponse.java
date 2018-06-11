package com.neotys.webpagetest.WPT;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WptResponse {
	public int statusCode;
	public String statusText;
	public Data data;

	@Override
	public String toString() {
		return "WptResponse{" +
				"statusCode=" + statusCode +
				", statusText='" + statusText + '\'' +
				", data=" + data +
				'}';
	}

	public class Data {
		@SerializedName(value = "id")
		public String testId;
		public Average average;
		public String url;
		public String summary;
		public String location;
		public String from;
		public String connectivity;
		public long completed;
		public List<Run> runs;

		@Override
		public String toString() {
			return "Data{" +
					"testId='" + testId + '\'' +
					", url=" + url +
					", summary=" + summary +
					", location=" + location +
					", from=" + from +
					", connectivity=" + connectivity +
					", completed=" + completed +
					", average=" + average +
					", runs=" + runs +

					'}';
		}
	}
	public class Pages{
		public String details;
		public String checklist;

		@Override
		public String toString() {
			return "Pages{" +
					" details=" + details +
					", checklist=" + checklist +
					'}';
		}
	}

	public class rawData{
		public String utilization;
		public String requestsData;
		public String pageData;
		@Override
		public String toString() {
			return "rawData{" +
					"utilization=" + utilization +
					", pageData=" + pageData +
					", requestsData=" + requestsData +
					'}';
		}
	}
	public class Average {
		public double loadTime;
		public double TTFB;
		public double bytesIn;
		public double bytesOut;
		public double bytesInDoc;
		public double bytesOutDoc;

		public double requestsDoc;
		public double render;
		public double fullyLoaded;
		public double docTime;
		public double domTime;
		public int avgRun;
		public double requestsFull;
		public double requests;
		public double firstLayout;
		public double domComplete;
		public double	 visualComplete;
		public double domInteractive;
		public double score_gzip;
		public double score_cdn;
		public double score_progressive_jpeg;
		public double score_etags;
		public double score_cache;
		public double score_keepalive;
		public double score_compress;
		public double SpeedIndex;

		public double responses_404;
		public double responses_200;
		public double gzip_total;
		public double image_total;


		public double connections;
		public double domElements;

		@Override
		public String toString() {
			return "Average{" +
					"loadTime=" + loadTime +
					", TTFB=" + TTFB +
					", bytesIn=" + bytesIn +
					", bytesOut=" + bytesOut+
					", bytesInDoc=" + bytesInDoc +
					", bytesOutDoc=" + bytesOutDoc+
					", requests=" + requests +
					", requestsDoc=" + requestsDoc +
					", requestsFull=" + requestsFull +
					", render=" + render +
					", fullyLoaded=" + fullyLoaded +
					", docTime=" + docTime +
					", firstLayout=" + firstLayout +
					", domComplete=" + domComplete +
					", domTime=" + domTime +
					", visualComplete=" + visualComplete +
					", domInteractive=" + domInteractive +
					", score_gzip=" + score_gzip +
					", score_cdn=" + score_cdn +
					", score_progressive_jpeg=" + score_progressive_jpeg +
					", score_etags=" + score_etags +
					", score_cache=" + score_cache +
					", score_keepalive=" + score_keepalive +
					", score_compress=" + score_compress +
					", SpeedIndex=" + SpeedIndex +
					", responses_404=" + responses_404 +
					", responses_200=" + responses_200 +
					", gzip_total=" + gzip_total +
					", connections=" + connections +
					", image_total=" + image_total +
					", domElements=" + domElements +
					", avgRun=" + avgRun +
					'}';
		}
	}

	public class Run {
		public int loadTime;
		public double TTFB;
		public double bytesIn;
		public double bytesOut;
		public double bytesInDoc;
		public double bytesOutDoc;

		public double requestsDoc;
		public int render;
		public int fullyLoaded;
		public int docTime;
		public int domTime;
		public int avgRun;
		public double requestsFull;
	//	public int requests;
		public int firstLayout;
		public int domComplete;
		public int visualComplete;
		public double domInteractive;
		public int score_gzip;
		public int score_cdn;
		public int score_progressive_jpeg;
		public int score_etags;
		public int score_cache;
		public int score_keepalive;
		public int score_compress;
		public String SpeedIndex;

		public int responses_404;
		public int responses_200;
		public double gzip_total;
		public long image_total;


		public double connections;
		public long domElements;

		public Pages pages;
		public rawData rawData;




		@Override
		public String toString() {
			return "Run{" +
					"loadTime=" + loadTime +
					", TTFB=" + TTFB +
					", bytesIn=" + bytesIn +
					", bytesOut=" + bytesOut+
					", bytesInDoc=" + bytesInDoc +
					", bytesOutDoc=" + bytesOutDoc+
	//				", requests=" + requests +
					", requestsDoc=" + requestsDoc +
					", requestsFull=" + requestsFull +
					", render=" + render +
					", fullyLoaded=" + fullyLoaded +
					", docTime=" + docTime +
					", firstLayout=" + firstLayout +
					", domComplete=" + domComplete +
					", domTime=" + domTime +
					", visualComplete=" + visualComplete +
					", domInteractive=" + domInteractive +
					", score_gzip=" + score_gzip +
					", score_cdn=" + score_cdn +
					", score_progressive_jpeg=" + score_progressive_jpeg +
					", score_etags=" + score_etags +
					", score_cache=" + score_cache +
					", score_keepalive=" + score_keepalive +
					", score_compress=" + score_compress +
					", SpeedIndex=" + SpeedIndex +
					", score_compress=" + score_compress +
					", responses_404=" + responses_404 +
					", responses_200=" + responses_200 +
					", gzip_total=" + gzip_total +
					", connections=" + connections +
					", image_total=" + image_total +
					", domElements=" + domElements +
					", avgRun=" + avgRun +
					", pages=" + pages +
					", rawData=" + rawData +
					'}';
		}
	}

/*	public static void main(String[] args) throws IOException, URISyntaxException {
		final Gson gson = new GsonBuilder().registerTypeAdapterFactory(new MyAdapterFactory()).create();
		final String json = Files.toString(new File("/home/hrexed/wpt2.json"), Charsets.UTF_8);

		final WptResponse response = gson.fromJson(json, WptResponse.class);
		System.out.println(response.toString());
	}*/

	static class MyAdapterFactory implements TypeAdapterFactory {

		public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
			final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
			return new TypeAdapter<T>() {
				public void write(JsonWriter out, T value) throws IOException {
					delegate.write(out, value);
				}

				public T read(JsonReader in) throws IOException {
					if (type.getType().equals(Average.class)) {
						in.beginObject();
						T t = null;
						while (in.hasNext()) {
							String name = in.nextName();
							if (name.equals("firstView")) {
								t = delegate.read(in);
							} else {
								in.skipValue();
							}

						}
						in.endObject();
						return t;
					}
					if (type.getRawType().equals(List.class) && ((ParameterizedType)type.getType()).getActualTypeArguments()[0].equals(Run.class)) {
						if (in.peek() == JsonToken.NULL) {
							in.nextNull();
							return null;
						}

						Collection<Run> collection = new ArrayList<>();
						in.beginObject();
						while (in.hasNext()) {
							in.nextName();
							in.beginObject();
							while (in.hasNext()) {
								String name = in.nextName();
								if (name.equals("firstView")) {
									collection.add(gson.getAdapter(Run.class).read(in));
								} else {
									in.skipValue();
								}
							}
							in.endObject();
						}
						in.endObject();
						return (T) collection;
					}
					return delegate.read(in);
				}
			};
		}
	}
}
