package tn.esprit.spring.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;
import static org.mockito.ArgumentMatchers.anyLong;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseServicesImplTest {
/*
@Autowired
    ICourseServices iCourseServices;
    @Test
    public void retrieveAllCoursesTest()
    {
        List<Course> courseList=iCourseServices.retrieveAllCourses();
        Assertions.assertEquals(0,courseList.size());
    }
*/
@Autowired
ICourseServices iCourseServices;

    @Test
    public void testAddCourse() {
        // Create a new Course object to add
        Course newCourse = new Course();
        // Set properties for the new course
        newCourse.setLevel(18); // Set level
        newCourse.setTypeCourse(TypeCourse.COLLECTIVE_ADULT); // Set type of course
        newCourse.setSupport(Support.SKI);
        // Set other properties as required...

        // Add the course
        Course addedCourse = iCourseServices.addCourse(newCourse);

        // Ensure that the returned course is not null
        assertNotNull(addedCourse);
        // You might also check the generated course ID or other attributes as needed.

        // You can perform additional assertions to verify the added course if required
        // For instance, check the ID, type, level, or any other properties to validate the addition.
    }
    //------------------------------------------------------------------------
    //update test

    @Test
    public void testUpdateCourse() {
        // Create a new Course object to update
        Course existingCourse = iCourseServices.retrieveCourse(1L); // Replace 1L with the existing course ID
        assertNotNull(existingCourse, "Course to update must exist");

        // Modify some properties of the existing course
        existingCourse.setLevel(15); // Update level
        // Modify other properties as needed...

        // Update the course
        Course updatedCourse = iCourseServices.updateCourse(existingCourse);

        assertNotNull(updatedCourse, "Updated course must not be null");

        // Perform assertions to verify if the course is updated correctly
        assertEquals(15, updatedCourse.getLevel(), "Level should be updated to 15");
        // Add more assertions based on the properties you modified and expect to change after the update.
    }

    //--------------------------------------------------------------------
    //retrieve test
   @Test
   public void testRetrieveCourse() {
       // Create a mock of ICourseServices
       ICourseServices iCourseServices = Mockito.mock(ICourseServices.class);

       // Assuming you have an existing Course object with ID 3
       Course existingCourse = new Course();
       existingCourse.setNumCourse(3L); // Representing an existing course with ID 3 in your system

       // Define the behavior of the retrieveCourse method to return the existing course for ID 3
       Mockito.when(iCourseServices.retrieveCourse(3L)).thenReturn(existingCourse);

       // Retrieve the course using the mocked ICourseServices for an existing course (ID 3)
       Course retrievedCourse = iCourseServices.retrieveCourse(3L);

       // Assertions to check if the retrieved course is not null for an existing ID (3)
       assertNotNull(retrievedCourse, "Retrieved course must not be null for an existing course");

       // Simulate the behavior when the course doesn't exist by passing a different ID (e.g., 2L)
       Course nonExistentCourse = iCourseServices.retrieveCourse(2L);

       // Assertion to check if a non-existent course returns null
       assertNull(nonExistentCourse, "Non-existent course should return null");
   }

}