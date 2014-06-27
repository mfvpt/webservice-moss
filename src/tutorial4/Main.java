package tutorial4;

import com.amazon.webservices.awsecommerceservice._2009_11_01.AWSECommerceService;
import com.amazon.webservices.awsecommerceservice._2009_11_01.AWSECommerceServicePortType;
import com.amazon.webservices.awsecommerceservice._2009_11_01.ItemSearchRequest;
import com.amazon.webservices.awsecommerceservice._2009_11_01.ItemSearchResponse;
import com.amazon.webservices.awsecommerceservice._2009_11_01.ItemSearch;
import com.amazon.webservices.awsecommerceservice._2009_11_01.Item;
import com.amazon.webservices.awsecommerceservice._2009_11_01.Items;

/**
 *
 * @author nmag
 */
public class Main {

    public static void main(String[] args) {

        try {
            // AWS Access Key ID
            java.lang.String awsAccessKeyId = "<introduzir a sua chave 'AWS Access Key ID'>";
            // AWS Secret Access Key
            java.lang.String awsSecretKey = "<introduzir a sua chave 'AWS Secret Access Key'>";

			// Define o Service
            AWSECommerceService awsECommerceService = new AWSECommerceService();
			// Define o handler do Service - para manipulacao da sua chave de acesso
            awsECommerceService.setHandlerResolver(new AwsHandlerResolver(awsSecretKey));
			// Define o Port Service
            AWSECommerceServicePortType awsECommerceServicePortType = awsECommerceService.getAWSECommerceServicePort();

            // Obter o objecto da Operacao
            ItemSearchRequest itemRequest = new ItemSearchRequest();

            // Preencher o pedido - neste caso Musica
            itemRequest.setSearchIndex("Music");
            itemRequest.setKeywords("U2");
            ItemSearch itemElement = new ItemSearch();
            itemElement.setAWSAccessKeyId(awsAccessKeyId);
            itemElement.getRequest().add(itemRequest);

            // Invocar o Web service
            ItemSearchResponse response = awsECommerceServicePortType.itemSearch(itemElement);

            // Listar todos os items
            for (Items itemList : response.getItems()) {
                for (Item item : itemList.getItem()) {
                    System.out.println("Item: " + item.getItemAttributes().getTitle() + " - Artista: " + item.getItemAttributes().getArtist());
                }
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    private Main() {
    }

}
