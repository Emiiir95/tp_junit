import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServiceCommandeTest {
    private DepotStock stockDisponible = reference -> 100;
    private ServiceCommande service;

    private Panier panier;
    private Article articleTest;

    @BeforeEach
    void initialiser() {
        service = new ServiceCommande(stockDisponible);
        panier = new Panier();
        articleTest = new Article("REF-001", "Cahier", 3.50);
    }

    @Test
    void commandeValideDoitRetournerUneCommande() {
        panier.ajouterArticle(articleTest, 2);
        Commande commande = service.passerCommande(panier, "CLIENT-42");
        assertNotNull(commande);
        assertEquals(7.0, commande.total(), 0.001);
    }

    @Test
    void panierVideDoitLeverIllegalStateException() {

        assertThrows(IllegalStateException.class,
                () -> service.passerCommande(panier, "C1"));
    }

    @Test
    void identifiantClientNulDoitLeverException() {

        assertThrows(IllegalStateException.class,
                () -> service.passerCommande(panier, null));
    }

    @Test
    void identifiantClientVideDoitLeverException() {

        assertThrows(IllegalStateException.class,
                () -> service.passerCommande(panier, ""));
    }

    @Test
    void stockInsuffisantDoitLeverStockInsuffisantException() {
        panier.ajouterArticle(articleTest, 101);

        assertThrows(StockInsuffisantException.class,
                () -> service.passerCommande(panier, "CLIENT-1"));
    }

    @Test
    void totalCommandeDoitCorrespondreAuTotalDuPanier() {
        panier.ajouterArticle(articleTest, 10);
        Commande commande = service.passerCommande(panier, "CLIENT-42");

        assertEquals(panier.calculerTotal(), commande.total(), 0.001);
    }
}