import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PanierReductionTest {
    @ParameterizedTest
    @CsvSource({
            "'', 100.0",
            "REDUC10, 90.0",
            "REDUC20, 80.0"
    })
    void calculerTotalDoitAppliquerLaBonneReduction(
            String code, double totalAttendu) {

        // Arranger
        Panier panier = new Panier();
        Article article = new Article("REF-001", "Classeur", 10.0);

        panier.ajouterArticle(article, 10); // sous-total = 100.0

        // Agir
        if (code != null && !code.isBlank()) {
            panier.appliquerCodeReduction(code.trim());
        }

        // Affirmer
        assertEquals(totalAttendu, panier.calculerTotal(), 0.001);
    }

    @ParameterizedTest(name = "ref={0}, qte={1}, prix={2} → IllegalArgumentException")
    @CsvSource(nullValues = "NULL", value = {
            "NULL, 1, 1.50",
            "REF-1, 0, 1.50",
            "REF-1, -3, 1.50",
            "REF-1, 1, -1.00"
    })
    void ajouterArticleInvalidDoitLeverIllegalArgumentException(
            String reference, int quantite, double prix) {
        assertThrows(IllegalArgumentException.class, () -> {
            Article article = new Article(reference, "Stylo", prix);
            new Panier().ajouterArticle(article, quantite);
        });
    }
}