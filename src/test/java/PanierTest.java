import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PanierTest {
    @Test
    void ajouterArticleDeitAugmenterLeNombreDeArticles() {
        // Arranger
        Panier panier = new Panier();
        Article article = new Article("REF-001", "Stylo bleu", 1.50);

        // Agir
        panier.ajouterArticle(article, 2);

        // Affirmer
        assertEquals(1, panier.nombreArticles());
    }

    @Test
    void calculerTotalDoitRetournerLaSommeDessousTotaux() {
        Panier panier = new Panier();
        Article article1 = new Article("REF-001", "Stylo bleu", 1.50);
        Article article2 = new Article("REF-002", "Stylo noir", 1.50);

        panier.ajouterArticle(article1, 3);
        panier.ajouterArticle(article2, 3);

        assertEquals(9.00, panier.calculerTotal());
    }

    @Test
    void panierVideDoitRetournerEstVideEgalTrue() {
        Panier panier = new Panier();

        assertTrue(panier.estVide());
    }

    @Test
    void panierAvecArticlesNeDoitPasEtreVide() {
        Panier panier = new Panier();
        Article article = new Article("REF-001", "Stylo bleu", 1.50);
        panier.ajouterArticle(article, 3);

        assertFalse(panier.estVide());
    }

}