# Feature Idea: Spring & JPA Annotation Folding Pack

**Hook:** Strip away repetitive Spring and JPA annotations to reveal core business logic.

**Why now?** Spring Boot adoption keeps rising and users see annotation clutter daily.

**Lift-Cost Score:** M

**Starter spec:**
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
- Replace annotations with `books: List<Book> // 1:N via author_id`.
- Show placeholder `@OneToMany[author_id]` before the field.
- Collapse block to icon with tooltip `OneToMany Book (author_id)`.

### Bidirectional OneToMany with `mappedBy`

```java
@Entity
class Order {
    @OneToMany(mappedBy = "order")
    List<LineItem> items;
}
```

Folding ideas:
- Display `items ⇆ LineItem (mappedBy="order")`.
- Replace annotations with `items: List<LineItem> // inverse 1:N`.
- Use placeholder `⇆1:N LineItem` ahead of the field.

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
- Collapse annotations to `courses ⇄ Course via student_course`.
- Show placeholder `@ManyToMany[student_course]` before the field.
- Replace with icon and tooltip `ManyToMany<Course>`.

### ManyToMany inverse side

```java
@Entity
class Course {
    @ManyToMany(mappedBy = "courses")
    Set<Student> students;
}
```

Folding ideas:
- Display `students ⇆ Student (mappedBy="courses")`.
- Replace annotations with `students: Set<Student> // inverse N:M`.
- Use placeholder `⇆N:M Student`.

