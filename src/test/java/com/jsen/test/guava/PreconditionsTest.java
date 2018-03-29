package com.jsen.test.guava;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * <p>
 *  guava Preconditions test
 * </p>
 *
 * @author jsen
 * @since 2018-03-28
 */
public class PreconditionsTest {

    @Test
    public void PreNotNull() {
        String arg1 = "jsen";
        Preconditions.checkNotNull(arg1, "arg1 is null");
        String arg2 = "";
        Preconditions.checkNotNull(arg2, "arg2 is null");
        String arg3 = null;
        Preconditions.checkNotNull(arg3, "arg3 is null"); // null
    }

    @Test
    public void PreBoolean() {
        boolean b1 = true;
        boolean b2 = false;
        Preconditions.checkArgument(b1);
        Preconditions.checkArgument(b2, "b2 is false"); // false
    }

    @Test
    public void PreArrayIndex() {
        List<String> list = Lists.newArrayList("arg1", "arg2", "arg3");
        Preconditions.checkElementIndex(0, list.size());
        Preconditions.checkElementIndex(1, list.size());
        Preconditions.checkElementIndex(2, list.size());

        Preconditions.checkElementIndex(3, list.size());// OutOfIndex
    }

    @Test
    public void PreArrayPosition() {
        List<String> list = Lists.newArrayList("arg1", "arg2", "arg3");
        Preconditions.checkPositionIndex(0, list.size());
        Preconditions.checkPositionIndex(1, list.size());
        Preconditions.checkPositionIndex(2, list.size());

        Preconditions.checkPositionIndex(3, list.size());

        Preconditions.checkPositionIndex(4, list.size());// OutOfIndex
    }

    @Test
    public void PreArrayPositions() {
        List<String> list = Lists.newArrayList("arg1", "arg2", "arg3");
        Preconditions.checkPositionIndexes(0, 3, list.size());

        // Preconditions.checkPositionIndexes(3, 4, list.size()); // IndexOutOfBoundsException
        Preconditions.checkPositionIndexes(-1, 3, list.size()); // IndexOutOfBoundsException

    }

    @Test
    public void PreOptional() {
        Optional<String> arg1 = Optional.of("s");
        Optional<String> arg2 = Optional.fromNullable("s");
        Optional<String> arg3 = Optional.fromNullable(null);
        Preconditions.checkNotNull(arg3, "arg3 is null");
        Optional<String> arg4 = Optional.of(null); // NullPointerException
        Optional<String> arg5 = Optional.absent();
    }



}
