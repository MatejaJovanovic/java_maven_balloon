package Test;

import Exceptions.EmptyListException;
import SolutionExamples.Solution;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SolutionTest {

    static Solution solution;

    @BeforeAll
    static void beforeAll() {

        //init testing conditionals
        solution = new Solution();
        //init mocks if any
    }

    @DisplayName("When empty string is passed to the method it returns false")
    @Test
    public void whenEmptyInputThenReturnFalse() {

        boolean result = solution.isValidString("");
        assertFalse(result);
    }

    @DisplayName("When String is passed to the method it returns a frequency hashmap")
    @Test
    public void whenStringInputReturnHashmap(){
        String input = "asasasasas";
        HashMap<Character, Integer> result = solution.traverseString(input);
        HashMap<Character, Integer> expected = new HashMap<>();
        expected.put('a',5);
        expected.put('s',5);
        assertEquals(expected,result);
    }

    @DisplayName("When null is passed to the method it returns an empty hashmap")
    @Test
    public void whenNullInputReturnEmpty(){
        String input = "";
        HashMap<Character, Integer> result = solution.traverseString(input);
        assertTrue(result.isEmpty());
    }
    @DisplayName("When empty string is passed to the method it returns an empty hashmap")
    @Test
    public void whenEmptyInputReturnEmpty(){
        String input = "";
        HashMap<Character, Integer> result = solution.traverseString(input);
        assertTrue(result.isEmpty());
    }
    @DisplayName("When string is passed to the method it returns true")
    @Test
    public void whenStringInputReturnTrue(){
        boolean result= solution.isValidString("fdsfsdjfhdsfjk");
        assertTrue(result);
    }

    @DisplayName("When null is passed to the method it returns false")
    @Test
    public void whenNullInputReturnFalse(){
        boolean result= solution.isValidString(null);
        assertFalse(result);
    }

    @DisplayName("When empty string is passed to the method it returns false")
    @Test
    public void whenEmptyInputReturnFalse(){
        boolean result= solution.isValidString("");
        assertFalse(result);
    }

    @DisplayName("When blank string is passed to the method it returns false")
    @Test
    public void whenBlankInputReturnFalse(){
        boolean result= solution.isValidString("  ");
        assertFalse(result);
    }
    @DisplayName("When frequency hashmaps are passed method returns the maximum number of iterations")
    @Test
    public void whenFrequencyHashmapReturnNumberOfIterations() throws EmptyListException {
        HashMap<Character, Integer> inputWordChars = new HashMap<>();
        HashMap<Character, Integer> charCount = new HashMap<>();
        List<Integer>  divList=new ArrayList<Integer>();
        List<Character> divChar=new ArrayList<Character>();
        inputWordChars.put('a',1);
        inputWordChars.put('s',1);
        charCount.put('a',5);
        charCount.put('s',5);
        int result_iterations = solution.rowComparison(inputWordChars,charCount,divList,divChar);
        assertEquals(5,result_iterations);
    }

    @DisplayName("When frequency hashmaps with insufficient character count are passed method returns the maximum number of iterations")
    @Test
    public void whenInsufficientFrequencyHashmapReturnNumberOfIterations() throws EmptyListException {
        HashMap<Character, Integer> inputWordChars = new HashMap<>();
        HashMap<Character, Integer> charCount = new HashMap<>();
        List<Integer>  divList=new ArrayList<Integer>();
        List<Character> divChar=new ArrayList<Character>();
        inputWordChars.put('a',1);
        inputWordChars.put('s',1);
        charCount.put('a',4);
        charCount.put('s',5);
        int result_iterations = solution.rowComparison(inputWordChars,charCount,divList,divChar);
        assertEquals(4,result_iterations);
    }

    //This needs a remake
    @DisplayName("When valid input is passed to the method it returns total number of iterations ")
    @Test
    public void whenValidInputReturnTotalNumberOfIterations () throws IOException {

        BufferedReader reader = mock(BufferedReader.class);
        when(reader.readLine()).thenReturn("Line 1 of some file",
                "Line 2 of some file",
                "Line 3 of some file",null);
        int result= solution.solution("Line",reader);
        assertEquals(result,3);
    }

    @DisplayName("When string with missing character is passed to the method it returns the total number of iterations ")
    @Test
    public void whenMisiingCharacterReturnTotalNumberOfIterations () throws IOException {

        BufferedReader reader = mock(BufferedReader.class);
        when(reader.readLine()).thenReturn("Line 1 of some file",
                "Li 2 of some file",
                "Line 3 of some file",null);
        int result= solution.solution("Line",reader);
        assertEquals(result,2);
    }

    @DisplayName("When string with insufficient characters is passed to the method it returns the total number of iterations ")
    @Test
    public void whenNotEnoughCharactersReturnTotalNumberOfIterations () throws IOException {

        BufferedReader reader = mock(BufferedReader.class);
        when(reader.readLine()).thenReturn("Line 1 of some file",
                "Lin 2 of some file",
                "Line 3 of some file",null);
        int result= solution.solution("Lineee",reader);
        assertEquals(result,2);
    }

    @Test
    public void whenNullInputTerminate() throws IOException {

        BufferedReader reader = mock(BufferedReader.class);
        when(reader.readLine()).thenReturn("Line 1 of some file",
                null,
                "Line 3 of some file",null);
        int result= solution.solution("Line",reader);
        assertEquals(result,1);
    }
    @Test
    public void whenMissingCharReturnTotalNumberOfIterations() throws IOException {

        BufferedReader reader = mock(BufferedReader.class);
        when(reader.readLine()).thenReturn("Line 1 of some file",
                " ",
                "Line 3 of some file",null);
        int result= solution.solution("Line",reader);
        assertEquals(result,1);
    }
    @DisplayName("When null buffered reader is passed return 0")
    @Test
    public void whenNullBufferedReaderCatch() throws IOException {

        int result= solution.solution("Line",null);
        assertEquals(0,result);
    }
    @DisplayName("When called method clears the input parameters")
    @Test
    public void isClear(){
        HashMap<Character, Integer> charCount = new HashMap<>();
        List<Integer>  divList=new ArrayList<Integer>();
        List<Character> divListChar=new ArrayList<Character>();
        divListChar.add('y');
        divListChar.add('z');
        divList.add(9);
        divList.add(8);
        charCount.put('a',5);
        charCount.put('s',5);
        solution.clear(charCount,divList, divListChar);
        assertTrue(charCount.isEmpty()&&divListChar.isEmpty()&&divList.isEmpty());
    }
    @DisplayName("When called method clears the input parameters, bad path")
    @Test
    public void isClearBadPath(){
        HashMap<Character, Integer> charCount = new HashMap<>();
        List<Integer>  divList=new ArrayList<Integer>();
        List<Character> divListChar=new ArrayList<Character>();
        divListChar.add('y');
        divListChar.add('z');
        divList.add(9);
        divList.add(8);
        charCount.put('a',5);
        charCount.put('s',5);
        solution.clear(charCount,divList, divListChar);
        divList.add(9);
        assertFalse(charCount.isEmpty()&&divListChar.isEmpty()&&divList.isEmpty());
    }
}