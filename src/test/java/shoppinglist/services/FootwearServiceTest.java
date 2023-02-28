package shoppinglist.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import shoppinglist.models.Footwear;
import shoppinglist.repositories.FootwearRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class FootwearServiceTest {
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @InjectMocks
    private Footwear footwear;
    @Mock
    private FootwearRepository footwearRepository;
    @InjectMocks
    private FootwearService footwearService;

    @Test
    public void testFindAllWithKeyword() {
        // Set up test data
        String keyword = "sneakers";
        List<Footwear> expectedFootwearList = new ArrayList<>();
        expectedFootwearList.add(new Footwear("Nike Air Max", 100.0));
        expectedFootwearList.add(new Footwear("Adidas Ultra Boost", 120.0));
        when(footwearRepository.searchALl(keyword)).thenReturn(expectedFootwearList);

               List<Footwear> actualFootwearList = footwearService.findAll(keyword);

              verify(footwearRepository, times(1)).searchALl(keyword);
        verify(footwearRepository, never()).findAll();
        assertEquals(expectedFootwearList, actualFootwearList);
    }
    @Test
    public void testFindAllWithoutKeyword() {
        // Set up test data
        List<Footwear> expectedFootwearList = new ArrayList<>();
        expectedFootwearList.add(new Footwear("Nike Air Max", 100.0));
        expectedFootwearList.add(new Footwear("Adidas Ultra Boost", 120.0));
        when(footwearRepository.findAll()).thenReturn(expectedFootwearList);

        // Call the method under test
        List<Footwear> actualFootwearList = footwearService.findAll(null);

        // Verify the result
        verify(footwearRepository, never()).searchALl(anyString());
        verify(footwearRepository, times(1)).findAll();
        assertEquals(expectedFootwearList, actualFootwearList);
    }
}