//package az.company.cardproject.service
//
//import az.company.cardproject.dao.entity.Card
//import az.company.cardproject.dao.repository.CardRepository
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.boot.test.mock.mockito.MockBean
//import spock.lang.Specification
//@SpringBootTest
//class CardServiceSpec extends Specification{
//    @Autowired
//    CardService cardService
//
//    @MockBean
//    CardRepository cardRepository
//
//    @MockBean
//    ValidationService validationService
//
//    def "test saveCards - valid card"() {
//        given: "a valid card"
//        Card card = new Card(pan: "1234567890123456")
//
//        and: "validationService returns true"
//        validationService.isCardNoValid(card.getPan()) >> true
//
//        when: "saveCards is called"
//        cardService.saveCards(card)
//
//        then: "createdAt and balance should be set and card should be saved"
//        1 * cardRepository.save(_ as Card) >> { Card savedCard ->
//            assert savedCard.getCreatedAt() != null
//            assert savedCard.getBalance() == BigDecimal.valueOf(0)
//        }
//    }
//
//    def "test saveCards - invalid card"() {
//        given: "an invalid card"
//        Card card = new Card(pan: "invalid")
//
//        and: "validationService returns false"
//        validationService.isCardNoValid(card.getPan()) >> false
//
//        when: "saveCards is called"
//        def e = thrown(CardNotValidException)
//
//        then: "an exception should be thrown with the correct message"
//        e.message == "Enter card number wrong"
//    }
//}
