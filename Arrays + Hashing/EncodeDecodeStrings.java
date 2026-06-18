// Design an algorithm to encode a list of
// strings to a string. The encoded string is
// then sent over the network and is decoded
// back to the original list of strings.

import java.util.ArrayList;
import java.util.List;

public class EncodeDecodeStrings {

    // well the question is really straight-forward
    // the issue is, it is open ended.
    // you have to make your own algorithm to encode
    // and decode.
    // i will just explain my approach as this is
    // just an open-ended question, you can make your
    // own encoding and decoding algorithm once you
    // get the thought process. also, this question
    // can be rephrased into a whole lot of other
    // questions, and in real world applications as well.
    // so i feel like this is one of the better questions.

    // take the list as : ["abc", "def#ghi", "3"]
    // bascially, right now, my encoding process is
    // take the first element from the list, calculate
    // its length, and then add the length and a special
    // delimiter of your choice, i used #.
    // i used this because of two reasons. these will explain
    // everything:
    // 1. why not just "#": if i use just # as delimiter,
    // see the second element.
    // the encoded string now becomes: abc#def#ghi#3. now
    // when i decode, suddenly its ambiguous. was the original
    // list abc, def, ghi, 3 or abc#def, ghi, 3, or abc, def#ghi, 3
    // what was it? abgiguity is the worst thing in comp sci
    // and engineers have fought wars with computers to
    // get rid of it.
    // 2. why not just length: well, if i just add the length,
    // see the third element, 3, the length is 1.
    // my string becomes 3abc6def#ghi13
    // now what? what is 13? what does it signify? is the length
    // 13? what even is the length? again, it got ambuiguious.
    // keeping length + '#' makes us definitely sure that
    // the string we will get will always be in the format:
    // length# .. content .. length# .. content .. ...
    // now even if the content contains # inside or a number inside,
    // we know for sure the fact that what is the length of
    // the content, so nothing can stop us from encoding it correctly.
    // what if we get a number# in the content itself!?
    // well, lol. its also covered under our insurance.
    // the first length in our string dictates how many characters
    // are supposed to be put in list when decoded.
    // lets say: "abc", "5#2", "def"
    // now: encoded string becomes: 3#abc3#5#23#def
    // this looks scary to you, but to the computer, it is just
    // another step of the algorithm.
    // it sees 3# first and is like: put the next 3 characters in
    // the array: so the decoded array becomes ["abc"]. now, it sends
    // the reader (decoder is like a mini parser) to exact 3 steps ahead
    // (which was the length of the substring), now it reads 3#, it says
    // the next three characters need to be put in the array. so array
    // becomes ["abc", "5#2"]. did you notice? it didnt care about whats
    // inside! it is really impressive, the algorithm suddently became
    // robust and unbreakable cuz we made it play with rules. (our syntax)
    // now again, the reader will jump 3 steps ahead and see 3# and put the
    // last 3 characters in the array, thus we got the original array,
    // well polished and decoded, completely unchanged: ["abc", "5#2", "def"].

    public static String encode(List<String> strs) {
        // to make my string on-the-fly, i use string builder
        StringBuilder sb = new StringBuilder();

        // to join the entire array in the format:
        // length#content ... length#content ...
        for (String s : strs) {
            // length + #
            int length = s.length();
            sb.append(length + "#");
            // content
            sb.append(s);
        }

        return sb.toString();
    }

    public static List<String> decode(String str) {
        List<String> strs = new ArrayList<>();
        int i = 0; // this is the reader

        while (i < str.length()) {
            int length = 0;

            // we read the length
            // well a few important questions:
            // 1. why are we not checking if the current
            // character is even the length?
            // well it is, trust the process. we built the algorithm in
            // such a way that it puts the contents in the array and skips
            // the content entirely, moving the reader forward by the length
            // of the content. this way, we are completely sure that we will
            // always end up in a length token.
            // 2. why are we doing the weird math inside?
            // well, who said that the length can't be multi-digit, the length
            // can be one digit, two digit, three digit, anything, so we just
            // consume it so that we form the number back again. this is
            // just a decimal number recovery trick i got as my second nature
            // due to my more than 10 years of programming experience, so i
            // really can't explain this. you can try to understand this by
            // some llm maybe idk.
            // this weird math allows us to even take care of lengths which
            // are multi-digit, making our little algo scalable and powerful.
            // we only stop once we encounter a #. remember our sweet little
            // syntax? length#content ?? we stop when i reads the length
            // and reaches #.

            while (str.charAt(i) != '#') {
                length = length * 10 + (str.charAt(i) - '0');
                i++;
            }

            // i has reached #, skip it and consume the content
            // and put it in the array.
            i++;

            // cut the string, take the content and put it in
            // the array.
            // this is just a substring. i is right now at the
            // beginning of the content block, so it takes everything
            // between the beginning of the content block, uptil
            // the end of the content block which is just the
            // current position + length of the block, upper
            // limit is excluded as usual.
            strs.add(str.substring(i, i + length));

            // remember we trusted the process when it came to
            // not check if i is a digit?
            // this is the process.
            // we just skip the content block entirely by adding the length
            // so before the starting of the next iteration, we are just
            // 1000% sure that it is the length of the next block
            // and always a digit, otherwise we have just reached the
            // end of the encoded string and its time to leave.
            i += length;
        }

        return strs;
    }

    public static void main(String[] args) {
        List<String> strs = new ArrayList<>();
        strs.add("abc");
        strs.add("def#ghi");
        strs.add("");
        strs.add("#ijlfjfj");

        String encoded = encode(strs);

        System.out.println(encoded);
        System.out.println(decode(encoded));
    }
}
