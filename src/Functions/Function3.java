package Functions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Functions.function3.*;
import Functions.function3.apiNFT.RaribleItem;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class Function3 {

    public static void main(String[] args) {
    	//Xử lý json
        List<RaribleItem> raribleItems = readRaribleJson(".\\src\\data\\outputData\\apiData\\rarible.json");

        // Tiến hành phân tích dữ liệu ở đây
        // Ví dụ: tính toán hệ số tương quan Pearson giữa giá và volume trên sàn Rarible
        double[] rariblePrices = raribleItems.stream()
                .mapToDouble(RaribleItem::getFloorPriceETH)
                .toArray();
        double[] raribleVolumes = raribleItems.stream()
                .mapToDouble(RaribleItem::getTotalItemSupply)
                .toArray();
        double correlationOpenseaPriceVolume = calculatePearsonCorrelation(rariblePrices, raribleVolumes);

        System.out.println("Correlation between Opensea Price and Volume: " + correlationOpenseaPriceVolume);
    }

    // Các phương thức đọc dữ liệu từ file JSON

    private static List<RaribleItem> readRaribleJson(String filename) {
        try (FileReader reader = new FileReader(filename)) {
            Gson gson = new Gson();
            // Sử dụng TypeToken để chỉ định kiểu dữ liệu của mảng
            Type collectionType = new TypeToken<ResultWrapper<RaribleItem>>(){}.getType();
            ResultWrapper<RaribleItem> resultWrapper = gson.fromJson(reader, collectionType);
            // Lấy danh sách RaribleItem từ thuộc tính "result"
            return resultWrapper.getResult();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    

    // Các phương thức tính toán và xử lý dữ liệu

    private static double calculatePearsonCorrelation(double[] array1, double[] array2) {
        // Tính mean của mỗi mảng
        double mean1 = calculateMean(array1);
        double mean2 = calculateMean(array2);

        // Tính tử số và mẫu số của hệ số tương quan Pearson
        double numerator = 0.0;
        double denominator1 = 0.0;
        double denominator2 = 0.0;

        int n = array1.length;
        for (int i = 0; i < n; i++) {
            numerator += (array1[i] - mean1) * (array2[i] - mean2);
            denominator1 += Math.pow(array1[i] - mean1, 2);
            denominator2 += Math.pow(array2[i] - mean2, 2);
        }

        // Tính hệ số tương quan Pearson
        double correlation;
        if (denominator1 != 0 && denominator2 != 0) {
            correlation = numerator / Math.sqrt(denominator1 * denominator2);
        } else {
            // Nếu mẫu số là 0, hệ số tương quan sẽ không xác định
            correlation = Double.NaN;
        }

        return correlation;
    }

    private static double calculateMean(double[] array) {
        double sum = 0.0;
        for (double value : array) {
            sum += value;
        }
        return sum / array.length;
    }
}
