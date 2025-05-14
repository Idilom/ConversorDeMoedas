package com.idilom.currencyconverter.terminal;

import com.idilom.currencyconverter.service.Services;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConvTerminal {
    static final Map<Integer, String[]> currencyPairs = Map.of(
            1, new String[]{"USD", "BRL"},
            2, new String[]{"EUR", "BRL"},
            3, new String[]{"JPY", "USD"},
            4, new String[]{"BRL", "USD"},
            5, new String[]{"GBP", "BRL"},
            6, new String[]{"AUD", "BRL"}
    );
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        double amount = 0;
        System.out.printf("\n" +
                "\n" +
                "  ____        _         ____                    __     ___           _                            \n" +
                " / ___|  ___ (_) __ _  | __ )  ___ _ __ ___     \\ \\   / (_)_ __   __| | ___     __ _  ___         \n" +
                " \\___ \\ / _ \\| |/ _` | |  _ \\ / _ \\ '_ ` _ \\ ____\\ \\ / /| | '_ \\ / _` |/ _ \\   / _` |/ _ \\        \n" +
                "  ___) |  __/| | (_| | | |_) |  __/ | | | | |_____\\ V / | | | | | (_| | (_) | | (_| | (_) |       \n" +
                " |____/ \\___|/ |\\__,_| |____/ \\___|_| |_| |_|      \\_/  |_|_| |_|\\__,_|\\___/   \\__,_|\\___/        \n" +
                "   ____    |__/                                       _        __  __                _            \n" +
                "  / ___|___  _ ____   _____ _ __ ___  ___  _ __    __| | ___  |  \\/  | ___   ___  __| | __ _ ___  \n" +
                " | |   / _ \\| '_ \\ \\ / / _ \\ '__/ __|/ _ \\| '__|  / _` |/ _ \\ | |\\/| |/ _ \\ / _ \\/ _` |/ _` / __| \n" +
                " | |__| (_) | | | \\ V /  __/ |  \\__ \\ (_) | |    | (_| |  __/ | |  | | (_) |  __/ (_| | (_| \\__ \\ \n" +
                "  \\____\\___/|_| |_|\\_/ \\___|_|  |___/\\___/|_|     \\__,_|\\___| |_|  |_|\\___/ \\___|\\__,_|\\__,_|___/ \n" +
                "                                                                                                  \n" +
                "\n");
        while (true) {
            System.out.printf("%nEscolha uma opção:%n%n");
            System.out.println("1. Dólar (USD) para Real (BRL)");
            System.out.println("2. Euro (EUR) para Real (BRL)");
            System.out.println("3. Yene (JPY) para Dólar (USD)");
            System.out.println("4. Real (BRL) para Dólar (USD)");
            System.out.println("5. Libra (GBP) para Real (BRL)");
            System.out.println("6. Dólar Australiano (AUD) para Real (BRL)");
            System.out.printf("7. Sair%n%n");

            System.out.print("Digite o número da opção: ");

            if (!scanner.hasNextInt()) {
                System.out.printf("%nPor favor, Escolha uma opção válida!%n");
                scanner.next(); // descarta a entrada inválida
                continue;
            }

            choice = scanner.nextInt();

            if (choice == 7) {
                System.out.println("Saindo...");
                break;
            } else if (choice < 1 || choice > 7) {
                System.out.println("Escolha uma opção válida!\n");
                continue;
            }

            System.out.print("Digite o valor que deseja converter: ");
            String input = scanner.next().replace(",", ".");

            try {
                amount = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Por favor, digite um número decimal válido (ex: 1.1 ou 1,1).");
                continue;
            }


            String from = currencyPairs.get(choice)[0];
            String to = currencyPairs.get(choice)[1];

            double conversionRate = Services.getConversionRate(from, to);
            if (conversionRate == -1) {
                System.out.println("Erro ao obter a taxa de conversão.");
                continue;
            }

            double convertedAmount = amount * conversionRate;
            System.out.printf("%nResultado: %.2f %s equivalem a %.2f %s%n%n", amount, from, convertedAmount, to);
        }

        scanner.close();
    }
}
