#Thanks to JohanC on Stack Overflow
#https://stackoverflow.com/questions/53883475/stars-and-bars-with-individual-maximum-partition-size

def find_partitions(x, lims):
    # partition the number x in a list of buckets;
    # the number of elements of each bucket i is strictly smaller than lims[i];
    # the sum of all buckets is x;
    # output the lists of buckets one by one

    a = [x] + [0 for l in lims[1:]]  # create an output array of the same lenghth as lims, set a[0] to x

    while True:

        # step 1: while a[i] is too large: redistribute to a[i+1]
        i = 0
        while a[i] >= lims[i] and i < len(lims) - 1:
            a[i + 1] += a[i] - (lims[i] - 1)
            a[i] = (lims[i] - 1)
            i += 1
        if a[-1] >= lims[-1]:
            return # the last bucket has too many elements: we've reached the last partition;
                   # this only happens when x is too large

        yield a

        # step 2:  add one to group 1;
        #    while a group i is already full: set to 0 and increment group i+1;
        #    while the surplus is too large (because a[0] is too small): repeat incrementing
        i0 = 1
        surplus = 0
        while True:
            for i in range(i0, len(lims)):  # increment a[i] by 1, which can carry to the left
                if a[i] < lims[i]-1:
                    a[i] += 1
                    surplus += 1
                    break
                else:  # a[i] would become too full if 1 were added, therefore clear a[i] and increment a[i+1]
                    surplus -= a[i]
                    a[i] = 0
            else:  # the for-loop didn't find a small enough a[i]
                return

            if a[0] >= surplus:   # if a[0] is large enough to absorb the surplus, this step is done
                break
            else:  # a[0] would get negative to when absorbing the surplus, set a[i0] to 0 and start incrementing a[i0+1]
                surplus -= a[i0]
                a[i0] = 0
                i0 += 1
                if i0 == len(lims):
                    return

        # step 3: a[0] should absorb the surplus created in step 2, although a[0] can get be too large
        a[0] -= surplus


x = 6
lims = [5, 4, 3, 2, 3, 3, 2, 2, 2, 5]
count = 0
multiplier = 1
for i, p in enumerate(find_partitions(x, lims)):
    multiplier = 1
    if p[0]==4 or p[0]==3 or p[0]==2:
        multiplier*=3
    elif p[0]==1:
        multiplier*=2
    if p[9]==4:
        multiplier*=21
    elif p[9]==3:
        multiplier*=16
    elif p[9]==2:
        multiplier*=9
    elif p[9]==1:
        multiplier*=4
    count+=multiplier
    print(f"partition {i+1}: {p} sums to {sum(p)}  lex: { ''.join([str(i) for i in p[::-1]]) }")
print("There are "+str(count)+" total number of ways to bag 6 ducks")