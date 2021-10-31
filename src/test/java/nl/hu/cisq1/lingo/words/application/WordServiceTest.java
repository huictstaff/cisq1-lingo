//package nl.hu.cisq1.lingo.words.application;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.any;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.Optional;
//
//import nl.hu.cisq1.lingo.words.domain.Word;
//import nl.hu.cisq1.lingo.lingoTrainer.domain.exception.WordLengthNotSupportedException;
//import nl.hu.cisq1.lingo.words.data.SpringWordRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//@ContextConfiguration(classes = {WordService.class})
//@ExtendWith(SpringExtension.class)
//class WordServiceTest {
//    @MockBean
//    private SpringWordRepository springWordRepository;
//
//    @Autowired
//    private WordService wordService;
//
//    @Test
//    void testConstructor() {
//        // TODO: This test is incomplete.
//        //   Reason: R002 Missing observers.
//        //   Diffblue Cover was unable to create an assertion.
//        //   Add getters for the following fields or make them package-private:
//        //     WordService.wordRepository
//
//        new WordService(mock(SpringWordRepository.class));
//    }
//
//    @Test
//    void testConstructor2() {
//        // TODO: This test is incomplete.
//        //   Reason: R002 Missing observers.
//        //   Diffblue Cover was unable to create an assertion.
//        //   Add getters for the following fields or make them package-private:
//        //     WordService.wordRepository
//
//        new WordService(mock(SpringWordRepository.class));
//    }
//
//    @Test
//    void testProvideRandomWord() {
//        when(this.springWordRepository.findRandomWordByLength((Integer) any())).thenReturn(Optional.<Word>of(new Word()));
//        assertNull(this.wordService.provideRandomWord(3));
//        verify(this.springWordRepository).findRandomWordByLength((Integer) any());
//    }
//
//    @Test
//    void testProvideRandomWord2() {
//        Word word = mock(Word.class);
//        when(word.getValue()).thenReturn("42");
//        Optional<Word> ofResult = Optional.<Word>of(word);
//        when(this.springWordRepository.findRandomWordByLength((Integer) any())).thenReturn(ofResult);
//        assertEquals("42", this.wordService.provideRandomWord(3));
//        verify(this.springWordRepository).findRandomWordByLength((Integer) any());
//        verify(word).getValue();
//    }
//
//    @Test
//    void testProvideRandomWord3() {
//        when(this.springWordRepository.findRandomWordByLength((Integer) any())).thenReturn(Optional.<Word>empty());
//        assertThrows(WordLengthNotSupportedException.class, () -> this.wordService.provideRandomWord(3));
//        verify(this.springWordRepository).findRandomWordByLength((Integer) any());
//    }
//
//    @Test
//    void testProvideRandomWord4() {
//        when(this.springWordRepository.findRandomWordByLength((Integer) any())).thenReturn(Optional.<Word>of(new Word()));
//        assertNull(this.wordService.provideRandomWord(3));
//        verify(this.springWordRepository).findRandomWordByLength((Integer) any());
//    }
//
//    @Test
//    void testProvideRandomWord5() {
//        Word word = mock(Word.class);
//        when(word.getValue()).thenReturn("42");
//        Optional<Word> ofResult = Optional.<Word>of(word);
//        when(this.springWordRepository.findRandomWordByLength((Integer) any())).thenReturn(ofResult);
//        assertEquals("42", this.wordService.provideRandomWord(3));
//        verify(this.springWordRepository).findRandomWordByLength((Integer) any());
//        verify(word).getValue();
//    }
//
//    @Test
//    void testProvideRandomWord6() {
//        when(this.springWordRepository.findRandomWordByLength((Integer) any())).thenReturn(Optional.<Word>empty());
//        assertThrows(WordLengthNotSupportedException.class, () -> this.wordService.provideRandomWord(3));
//        verify(this.springWordRepository).findRandomWordByLength((Integer) any());
//    }
//}//package nl.hu.cisq1.lingo.words.application;
////
////import nl.hu.cisq1.lingo.words.data.SpringWordRepository;
////import nl.hu.cisq1.lingo.words.domain.Word;
////import nl.hu.cisq1.lingo.lingoTrainer.domain.exception.WordLengthNotSupportedException;
////import org.junit.jupiter.api.DisplayName;
////import org.junit.jupiter.api.Test;
////import org.junit.jupiter.params.ParameterizedTest;
////import org.junit.jupiter.params.provider.Arguments;
////import org.junit.jupiter.params.provider.MethodSource;
////
////import java.util.Optional;
////import java.util.stream.Stream;
////
////import static org.junit.jupiter.api.Assertions.assertEquals;
////import static org.junit.jupiter.api.Assertions.assertThrows;
////import static org.mockito.Mockito.*;
////
/////**
//// * This is a unit test.
//// *
//// * It tests the behaviors of our system under test,
//// * WordService, in complete isolation:
//// * - its methods are called by the test framework instead of a controller
//// * - the WordService calls a test double instead of an actual repository
//// */
////class WordServiceTest {
////
////    @ParameterizedTest
////    @DisplayName("requests a random word of a specified length from the repository")
////    @MethodSource("randomWordExamples")
////    void providesRandomWord(int wordLength, String word) {
////        SpringWordRepository mockRepository = mock(SpringWordRepository.class);
////        when(mockRepository.findRandomWordByLength(wordLength))
////                .thenReturn(Optional.of(new Word(word)));
////
////        WordService service = new WordService(mockRepository);
////        String result = service.provideRandomWord(wordLength);
////
////        assertEquals(word, result);
////    }
////
////    @Test
////    @DisplayName("throws exception if length not supported")
////    void unsupportedLength() {
////        SpringWordRepository mockRepository = mock(SpringWordRepository.class);
////        when(mockRepository.findRandomWordByLength(anyInt()))
////                .thenReturn(Optional.empty());
////
////        WordService service = new WordService(mockRepository);
////
////        assertThrows(
////                WordLengthNotSupportedException.class,
////                () -> service.provideRandomWord(5)
////        );
////    }
////
////    static Stream<Arguments> randomWordExamples() {
////        return Stream.of(
////                Arguments.of(5, "tower"),
////                Arguments.of(6, "castle"),
////                Arguments.of(7, "knights")
////        );
////    }
////}