// Given an unsorted integer array nums. Return
// the smallest positive integer that is not present in nums.
// you must implement an algorithm that runs in O(n)
// time and uses O(1) auxiliary space.

public class FirstMissingPositive {

    // this is one of those questions where if you dont
    // scratch your head, you wont find the answer
    // the answer always lies in plain sight, its only
    // the constraints that make them invisible
    // the constraint asks you to do it in O(1) space
    // that means you have to come up with something which
    // does everything in-place, that is, it modifies the
    // original array. asking for O(n) time means they do
    // not want you to do sorting, but honestly, once you
    // understand, you wouldn't need sorting

    public static int firstMissingPositive(int[] nums) {
        // the answer basically works in two passes.
        // the first pass is the major one.

        // so to explore the quesiton, and how the solution works
        // imagine you're in a classroom, where seats are marked
        // with numbers. there is a seat with number zero, then
        // number one, then two and so on. in the seat with number
        // zero, only the student with the roll number 1 is allowed
        // to sit, and in seat number one, only student with roll number
        // 2 is allowed to sit. so basically, the seat numbers are
        // associated with roll numbers directly.

        // now take a deep breath, and if you understand what i said above,
        // start to read below.
        // now, every student came in, they had roll numbers, they sat and
        // they didn't really care about sitting on the right places much.
        // you are their teacher and you entered. you saw them and knew
        // that they are not sitting in the places they are supposed to.
        // you have a few options in mind.

        // trust me, it is really easy now. i can't really explain how
        // do you come up to this solution, but once you read this,
        // you will just hit your head once thinking why wasnt i able
        // to think of this myself.

        // okay. so take every student, and see their roll numbers. yes.
        // say you picked a student and their roll number. imagine the
        // roll number was three. you know that he is supposed to
        // sit on the bench with index zero. you send him there.
        // thats it. this is how the solution is formed. easy!
        // try to find it yourself now, only read after a few tries.

        // okay. so now, we really know the idea. now instead of
        // calling them one by one, you go to their places one by one
        // and then check their roll numbers, if they belong to the
        // correct position, well and good. but if they don't, well,
        // tell them to go to the correct position and the other
        // student comes. you check their roll number again, you do this
        // until you are left with someone sepcial.

        // the special: the students who don't belong to the class and
        // the students who have negative roll numbers (?). irl these
        // wouldnt exist, but for the question's sake, lets assume.
        // if they have any of these properties, well, we can't really
        // send them to any place, cuz they have not got any place.
        // where will you send someone who does not belong to the class,
        // or someone with an imaginary negative roll number, well
        // we will just skip and move to the next place. we have faith
        // in our method, and we just know that at the end of the process,
        // we will send almost everyone to their correct place.
        // the last special student is a twin (?). two students with the
        // same roll numbers. well in reality they wouldn't exist, but
        // again for the namesake of the question, assume they are there.
        // well you again can't do anything with them, so just skip them.
        // so say, i am at seat number 0 and student with a roll 3 is sitting
        // there, i wanna send the student to seat 2. but i find out that
        // the student on seat 2 is another student with the same roll
        // number. if i bring him here, i will just keep swapping these
        // two students. the best way to deal with this is, just skip them.

        // so after reading all that, lets come to write!
        // pass 1 - bring every student to their place.
        // > 0 and <= length makes sure that the student actually belongs
        // to the class cuz roll numbers are tied to the range 1 and the number
        // of seats available in the class.
        // != i + 1 just makes sure that the student sitting here is at the
        // right place or not. this will return true if a wrong student is
        // sitting here.
        // and lastly, nums[nums[i] - 1] just means that we absolutely
        // not want the student to swap with a student with the
        // same roll number.
        for (int i = 0; i < nums.length; i++) {
            while (
                nums[i] > 0 &&
                nums[i] <= nums.length &&
                nums[i] != i + 1 &&
                nums[i] != nums[nums[i] - 1]
            ) {
                // this is the correct seat number the current
                // student needs to be in.
                int correctIndex = nums[i] - 1;

                // just swapping
                int temp = nums[i];
                nums[i] = nums[correctIndex];
                nums[temp - 1] = temp;
            }
        }

        // now, we go on from seat 0 to the last seat.
        // if on seat 0, the student number 1 is not sitting,
        // voila they are missing.
        // same with any seat, if on seat i, the student with
        // the roll i + 1 is not sitting, they are missing.
        // we absolutely firmly believe in our algorithm.
        // btw 67

        // pass two: actual check
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) return i + 1; // student number i + 1 is missing, where i is the seat number.
        }

        // if no one was missing, well, the next student is missing lol
        // this was never about students. it was the friends we made
        // along the way.

        // okay, jokes aside, we tell that the next missing positive number
        // is the first missing positive number, that is all numbers from
        // 0 to length are already in correct positions, so the number length + 1
        // is missing.
        return nums.length + 1;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 0 };
        int[] nums2 = { 3, 4, -1, 1 };
        int[] nums3 = { 7, 8, 9, 11, 12 };

        System.out.println(firstMissingPositive(nums));
        System.out.println(firstMissingPositive(nums2));
        System.out.println(firstMissingPositive(nums3));
    }
}
