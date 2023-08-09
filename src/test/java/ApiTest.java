import client.ApiException;
import client.api.PetApi;
import client.model.Pet;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiTest {

    @Test
    public void TestCreatePet() throws ApiException {
        // Create
        PetApi petApi = new PetApi();
        Pet newPet = new Pet();
        Long petId = Long.parseLong("100");
        newPet.setId(petId);
        String petName = "PesPatron";
        newPet.setName(petName);
        Pet petDetails = petApi.addPet(newPet);
        Assert.assertEquals(petDetails.getId(), petId);

        // Get
        Pet returnedPet = petApi.getPetById(petId);
        Assert.assertEquals(returnedPet.getId(), petId);
        Assert.assertEquals(returnedPet.getName(), petName);

        // Update
        String updatedName = "GeneralPesPatron;";
        newPet.setName(updatedName);
        petApi.updatePet(newPet);

        // Get Updates
        returnedPet = petApi.getPetById(petId);
        Assert.assertEquals(returnedPet.getId(), petId);
        Assert.assertEquals(returnedPet.getName(), updatedName);

        // Delete
        petApi.deletePet(petId, "");

        try {
            petApi.getPetById(petId);
        } catch (ApiException e) {
            Assert.assertEquals(404, e.getCode());
        }


    }

}
