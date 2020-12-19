package ec.gob.mag.rna.predio.domain.constraints;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.google.common.base.Objects;

public class OneOfCultivosValidator implements ConstraintValidator<OneOfCultivos, Object> {

	private boolean required;

	private static final int[] agricola = new int[] { 763, 764, 765, 766, 767, 773, 774, 775, 776, 777, 778, 779, 780,
			781, 782, 783, 784, 785, 786, 787, 788, 789, 790, 791, 792, 793, 794, 795, 796, 797, 798, 799, 800, 801,
			802, 803, 804, 805, 806, 807, 808, 809, 810, 811, 812, 813, 814, 815, 816, 817, 818, 819, 820, 821, 822,
			823, 824, 825, 826, 827, 828, 829, 830, 831, 832, 833, 834, 835, 836, 837, 838, 839, 840, 841, 842, 843,
			844, 845, 846, 847, 848, 849, 850, 851, 852, 853, 854, 855, 856, 857, 858, 859, 860, 861, 862, 863, 864,
			865, 866, 867, 868, 869, 870, 871, 872, 873, 874, 875, 876, 877, 878, 879, 880, 881, 882, 883, 884, 885,
			886, 887, 888, 889, 890, 891, 892, 893, 894, 895, 896, 897, 898, 899, 900, 901, 902, 903, 904, 905, 906,
			907, 908, 909, 910, 911, 912, 913, 914, 915, 916, 917, 918, 919, 920, 921, 922, 923, 924, 925, 926, 927,
			928, 929, 930, 931, 932, 933, 934, 935, 936, 937, 938, 939, 940, 941, 942, 943, 944, 945, 946, 947, 948,
			949, 950, 951, 952, 953, 954, 955, 956, 957, 958, 959, 960, 961, 962, 963, 964, 965, 966, 967, 968, 969,
			970, 971, 972, 973, 974, 975, 976, 977, 978, 979, 980, 981, 982, 983, 984, 985, 986, 987, 988, 989, 990,
			991, 992, 993, 994, 995, 996, 997, 998, 999, 1000, 1001, 1002, 1003, 1004, 1005, 1006, 1007, 1008, 1009,
			1010, 1011, 1012, 1013, 1014, 1015, 1016, 1017, 1018, 1019, 1020, 1021, 1022, 1023, 1024, 1025, 1026, 1027,
			1028, 1029, 1030, 1031, 1032, 1033, 1034, 1035, 1036, 1037, 1038, 1039, 1040, 1041, 1042, 2791, 2792, 2793,
			2794, 2795, 2796, 2797, 2798, 2799, 2800, 2801, 2802, 2803, 2804, 2805, 2806, 2807, 2808, 2809, 2810, 2811,
			2812, 2813, 2814, 2815, 2816, 2817, 2818, 2819, 2820 };

//	private static final List<Integer> forestal = Arrays.asList(991, 992, 993, 994, 995, 996, 997, 998, 999, 1000, 1001,
//			1002, 1003, 1004, 1005, 1006, 1007, 2790);
//
//	private static final List<Integer> pecuario = Arrays.asList(869, 849, 850, 851, 852, 853, 854, 855, 856, 857, 858,
//			859, 860, 861, 863, 862, 1132, 1133);

	@Override
	public void initialize(OneOfCultivos constraintAnnotation) {
		this.required = constraintAnnotation.required();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (required) {
			Integer val = Integer.parseInt(value.toString());
			return Arrays.stream(agricola).anyMatch(s -> Objects.equal(val, s));
		} else {
			if (value != null) {
				Integer val = Integer.parseInt(value.toString());
				return Arrays.stream(agricola).anyMatch(s -> Objects.equal(val, s));
			}
			return true;
		}
	}
}