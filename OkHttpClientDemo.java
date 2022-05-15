package week02;

public class OkHttpClientDemo {

    public static void main(String[] args) {
        String url = "http://localhost:8801/";

        Request request = new Request.Builder().url(url).build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }
}
