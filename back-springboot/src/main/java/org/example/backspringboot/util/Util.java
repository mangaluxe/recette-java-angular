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
     * Génère un slug à partir d'une chaîne (supprime les accents, espaces, caractères spéciaux).
     */
    public static String slugify(String input) {
        if (input == null || input.isBlank()) {
            return null;
        }

        // Supprimer les accents :
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");

        // Remplacer caractères non alphanumériques par des tirets :
        String slug = normalized.replaceAll("[^\\p{Alnum}]+", "-")
                .replaceAll("^-|-$", "") // Supprimer tirets début/fin
                .toLowerCase();

        return slug;
    }

}
