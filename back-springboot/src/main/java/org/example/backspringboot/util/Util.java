package org.example.backspringboot.util;

import java.text.Normalizer;

public class Util {

    // ========== Propriétés ==========

    // ========== Constructeur ==========

    private Util() {
        // Constructeur privé pour empêcher l’instanciation
    }


    // ========== Méthodes ==========

    /**
     * Convertir une chaîne en slug
     */
    public static String slugify(String input) {
        if (input == null || input.isBlank()) {
            return "";
        }

        // Supprime les accents :
        String normalized = Normalizer.normalize(input.trim(), Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");

        // Remplace caractères non alphanumériques par des tirets :
        String slug = normalized.replaceAll("[^\\p{Alnum}]+", "-")
                .replaceAll("^-|-$", "") // Supprimer tirets début/fin
                .toLowerCase();

        return slug;
    }


    /**
     * Filtrer les gros mots dans une chaîne
     */
    public static String badWordFilter(String str) {
        String[] badWords = {"connard", "encul", "fuck", "nique ta", "pouffias", "poufias", "pute", "salop"};

        // Remplacer chaque gros mot par "***interdit***"
        for (String badWord : badWords) {
            str = str.replaceAll("(?i)" + badWord, "***interdit***"); // (?i) permet de rendre la recherche insensible à la casse
        }

        return str;
    }

}
