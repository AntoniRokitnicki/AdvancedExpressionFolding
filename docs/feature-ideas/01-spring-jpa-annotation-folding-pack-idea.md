# Feature Idea: Spring & JPA Annotation Folding Pack

**Hook:** Strip away repetitive Spring and JPA annotations to reveal core business logic.

**Why now?** Spring Boot adoption keeps rising and users see annotation clutter daily.

**Lift-Cost Score:** M

**Starter spec:**
- Map common `@Component`, `@Transactional`, and JPA annotations to concise icons or placeholders.
- Toggle pack on/off in settings.
- Add regression tests for sample Spring entities and services.

## Collection Mapping Patterns on `@Entity`

These patterns focus on annotation combinations used on `@Entity` classes to map collections. Each pattern includes a concrete example and several folding ideas.

### OneToMany with JoinColumn

```java
@Entity
class Author {
    @OneToMany
    @JoinColumn(name = "author_id")
    List<Book> books;
}
```

Folding ideas:
1. Replace annotations with `books: List<Book> // 1:N via author_id`.
2. Show placeholder `@OneToMany[author_id]` before the field.
3. Collapse block to icon with tooltip `OneToMany Book (author_id)`.

### Bidirectional OneToMany with `mappedBy`

```java
@Entity
class Order {
    @OneToMany(mappedBy = "order")
    List<LineItem> items;
}
```

Folding ideas:
1. Display `items ⇆ LineItem (mappedBy="order")`.
2. Replace annotations with `items: List<LineItem> // inverse 1:N`.
3. Use placeholder `⇆1:N LineItem` ahead of the field.

### ManyToMany with JoinTable

```java
@Entity
class Student {
    @ManyToMany
    @JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    Set<Course> courses;
}
```

Folding ideas:
1. Collapse annotations to `courses ⇄ Course via student_course`.
2. Show placeholder `@ManyToMany[student_course]` before the field.
3. Replace with icon and tooltip `ManyToMany<Course>`.

### ManyToMany inverse side

```java
@Entity
class Course {
    @ManyToMany(mappedBy = "courses")
    Set<Student> students;
}
```

Folding ideas:
1. Display `students ⇆ Student (mappedBy="courses")`.
2. Replace annotations with `students: Set<Student> // inverse N:M`.
3. Use placeholder `⇆N:M Student`.

