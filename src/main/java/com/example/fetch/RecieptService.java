package com.example.fetch;

import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class RecieptService {

    private final Map<UUID, Reciept> receiptMap = new HashMap<>();

    public List<Reciept> findAllReceipts() {
        return new ArrayList<>(receiptMap.values());
    }

    public Reciept saveReceipt(Reciept receipt) {
        UUID id = getNextId();
        receipt.setId(id);
        receiptMap.put(id, receipt);
        return receipt;
    }

    public double getAlphaNumericPoints(Reciept receipt, double points) {
        for (char c : receipt.getRetailer().toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                points += 1;
            }
        }
        return points;
    }

    public double getRoundDollarPoints(Reciept receipt, double points) {
        if (receipt.getTotal() % 1 == 0) {
            points += 50;
        }
        return points;
    }

    public double getQuaterPoints(Reciept receipt, double points) {
        if (receipt.getTotal() % 0.25 == 0) {
            points += 25;
        }
        return points;
    }

    public double getTwoItemListPoints(Reciept receipt, double points) {
        int len = receipt.getItemList().size() / 2;
        points += len * 5;
        return points;
    }

    public double getOddDatePoints(Reciept receipt, double points) {
        String date = receipt.getPurchaseDate();
        String[] parts = date.split("-");
        int day = Integer.parseInt(parts[2]);

        if (day % 2 != 0) {
            points += 6;
        }

        return points;
    }

    public double getTimeIntervalPoints(Reciept receipt, double points) {
        LocalTime startTime = LocalTime.of(14, 0);
        LocalTime endTime = LocalTime.of(16, 0);
        if (receipt.getPurchaseTime().isAfter(startTime) && receipt.getPurchaseTime().isBefore(endTime)) {
            points += 10;
        }
        return points;
    }

    public double getTrimPoints(Reciept receipt, double points) {
        for (int i = 0; i < receipt.getItemList().size(); i++) {
            if (receipt.getItemList().get(i).shortDescription.trim().length() % 3 == 0) {
                points += (int) Math.ceil(receipt.getItemList().get(i).price * 0.2);

            }
        }
        return points;
    }

    public double findReceiptById(UUID id) {
        double points = 0;
        Reciept receipt = receiptMap.get(id);
        points = getAlphaNumericPoints(receipt, points)
                + getRoundDollarPoints(receipt, points)
                + getQuaterPoints(receipt, points)
                + getTwoItemListPoints(receipt, points)
                + getOddDatePoints(receipt, points)
                + getTimeIntervalPoints(receipt, points)
                + getTrimPoints(receipt, points);

        return points;
    }

    private synchronized UUID getNextId() {
        UUID uuid = UUID.randomUUID();
        return uuid;
    }
}
