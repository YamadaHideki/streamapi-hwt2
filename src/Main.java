import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> surnames = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    surnames.get(new Random().nextInt(surnames.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long under18 = persons.stream()
                .filter(age -> age.getAge() < 18)
                .count();

        List<String> listConscripts = persons.stream()
                .filter(p -> p.getAge() > 18 && p.getAge() < 27)
                .map(Person::getSurname)
                .collect(Collectors.toList());

        List<Person> listWorkablePersons = persons.stream()
                .filter(p ->
                        (
                            p.getSex().equals(Sex.WOMAN) && p.getAge() < 60 ||
                            p.getSex().equals(Sex.MAN) && p.getAge() < 65
                        ) && p.getAge() > 18)
                .sorted(Comparator.comparing(Person::getSurname))
                .collect(Collectors.toList());
    }
}
