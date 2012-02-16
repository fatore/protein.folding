//==============================================================================
// HeatedObject Color SCALE Class
//
//   Steve Pizer, UNC Chapel Hill (perceptually linearized)
// 
// AGG - Alexander Gee
//
// 041497 - created
//==============================================================================

package fsmvis.ColourScales;

import java.awt.Color;

public class HeatedObject 
{
	static public Color[] rgb = new Color[256];

	static
	{
		rgb[  0] = new Color(   0,   0,   0 );
		rgb[  1] = new Color(  35,   0,   0 );
		rgb[  2] = new Color(  52,   0,   0 );
		rgb[  3] = new Color(  60,   0,   0 );
		rgb[  4] = new Color(  63,   1,   0 );
		rgb[  5] = new Color(  64,   2,   0 );
		rgb[  6] = new Color(  68,   5,   0 );
		rgb[  7] = new Color(  69,   6,   0 );
		rgb[  8] = new Color(  72,   8,   0 );
		rgb[  9] = new Color(  74,  10,   0 );
		rgb[ 10] = new Color(  77,  12,   0 );
		rgb[ 11] = new Color(  78,  14,   0 );
		rgb[ 12] = new Color(  81,  16,   0 );
		rgb[ 13] = new Color(  83,  17,   0 );
		rgb[ 14] = new Color(  85,  19,   0 );
		rgb[ 15] = new Color(  86,  20,   0 );
		rgb[ 16] = new Color(  89,  22,   0 );
		rgb[ 17] = new Color(  91,  24,   0 );
		rgb[ 18] = new Color(  92,  25,   0 );
		rgb[ 19] = new Color(  94,  26,   0 );
		rgb[ 20] = new Color(  95,  28,   0 );
		rgb[ 21] = new Color(  98,  30,   0 );
		rgb[ 22] = new Color( 100,  31,   0 );
		rgb[ 23] = new Color( 102,  33,   0 );
		rgb[ 24] = new Color( 103,  34,   0 );
		rgb[ 25] = new Color( 105,  35,   0 );
		rgb[ 26] = new Color( 106,  36,   0 );
		rgb[ 27] = new Color( 108,  38,   0 );
		rgb[ 28] = new Color( 109,  39,   0 );
		rgb[ 29] = new Color( 111,  40,   0 );
		rgb[ 30] = new Color( 112,  42,   0 );
		rgb[ 31] = new Color( 114,  43,   0 );
		rgb[ 32] = new Color( 115,  44,   0 );
		rgb[ 33] = new Color( 117,  45,   0 );
		rgb[ 34] = new Color( 119,  47,   0 );
		rgb[ 35] = new Color( 119,  47,   0 );
		rgb[ 36] = new Color( 120,  48,   0 );
		rgb[ 37] = new Color( 122,  49,   0 );
		rgb[ 38] = new Color( 123,  51,   0 );
		rgb[ 39] = new Color( 125,  52,   0 );
		rgb[ 40] = new Color( 125,  52,   0 );
		rgb[ 41] = new Color( 126,  53,   0 );
		rgb[ 42] = new Color( 128,  54,   0 );
		rgb[ 43] = new Color( 129,  56,   0 );
		rgb[ 44] = new Color( 129,  56,   0 );
		rgb[ 45] = new Color( 131,  57,   0 );
		rgb[ 46] = new Color( 132,  58,   0 );
		rgb[ 47] = new Color( 134,  59,   0 );
		rgb[ 48] = new Color( 134,  59,   0 );
		rgb[ 49] = new Color( 136,  61,   0 );
		rgb[ 50] = new Color( 137,  62,   0 );
		rgb[ 51] = new Color( 137,  62,   0 );
		rgb[ 52] = new Color( 139,  63,   0 );
		rgb[ 53] = new Color( 139,  63,   0 );
		rgb[ 54] = new Color( 140,  65,   0 );
		rgb[ 55] = new Color( 142,  66,   0 );
		rgb[ 56] = new Color( 142,  66,   0 );
		rgb[ 57] = new Color( 143,  67,   0 );
		rgb[ 58] = new Color( 143,  67,   0 );
		rgb[ 59] = new Color( 145,  68,   0 );
		rgb[ 60] = new Color( 145,  68,   0 );
		rgb[ 61] = new Color( 146,  70,   0 );
		rgb[ 62] = new Color( 146,  70,   0 );
		rgb[ 63] = new Color( 148,  71,   0 );
		rgb[ 64] = new Color( 148,  71,   0 );
		rgb[ 65] = new Color( 149,  72,   0 );
		rgb[ 66] = new Color( 149,  72,   0 );
		rgb[ 67] = new Color( 151,  73,   0 );
		rgb[ 68] = new Color( 151,  73,   0 );
		rgb[ 69] = new Color( 153,  75,   0 );
		rgb[ 70] = new Color( 153,  75,   0 );
		rgb[ 71] = new Color( 154,  76,   0 );
		rgb[ 72] = new Color( 154,  76,   0 );
		rgb[ 73] = new Color( 154,  76,   0 );
		rgb[ 74] = new Color( 156,  77,   0 );
		rgb[ 75] = new Color( 156,  77,   0 );
		rgb[ 76] = new Color( 157,  79,   0 );
		rgb[ 77] = new Color( 157,  79,   0 );
		rgb[ 78] = new Color( 159,  80,   0 );
		rgb[ 79] = new Color( 159,  80,   0 );
		rgb[ 80] = new Color( 159,  80,   0 );
		rgb[ 81] = new Color( 160,  81,   0 );
		rgb[ 82] = new Color( 160,  81,   0 );
		rgb[ 83] = new Color( 162,  82,   0 );
		rgb[ 84] = new Color( 162,  82,   0 );
		rgb[ 85] = new Color( 163,  84,   0 );
		rgb[ 86] = new Color( 163,  84,   0 );
		rgb[ 87] = new Color( 165,  85,   0 );
		rgb[ 88] = new Color( 165,  85,   0 );
		rgb[ 89] = new Color( 166,  86,   0 );
		rgb[ 90] = new Color( 166,  86,   0 );
		rgb[ 91] = new Color( 166,  86,   0 );
		rgb[ 92] = new Color( 168,  87,   0 );
		rgb[ 93] = new Color( 168,  87,   0 );
		rgb[ 94] = new Color( 170,  89,   0 );
		rgb[ 95] = new Color( 170,  89,   0 );
		rgb[ 96] = new Color( 171,  90,   0 );
		rgb[ 97] = new Color( 171,  90,   0 );
		rgb[ 98] = new Color( 173,  91,   0 );
		rgb[ 99] = new Color( 173,  91,   0 );
		rgb[100] = new Color( 174,  93,   0 );
		rgb[101] = new Color( 174,  93,   0 );
		rgb[102] = new Color( 176,  94,   0 );
		rgb[103] = new Color( 176,  94,   0 );
		rgb[104] = new Color( 177,  95,   0 );
		rgb[105] = new Color( 177,  95,   0 );
		rgb[106] = new Color( 179,  96,   0 );
		rgb[107] = new Color( 179,  96,   0 );
		rgb[108] = new Color( 180,  98,   0 );
		rgb[109] = new Color( 182,  99,   0 );
		rgb[110] = new Color( 182,  99,   0 );
		rgb[111] = new Color( 183, 100,   0 );
		rgb[112] = new Color( 183, 100,   0 );
		rgb[113] = new Color( 185, 102,   0 );
		rgb[114] = new Color( 185, 102,   0 );
		rgb[115] = new Color( 187, 103,   0 );
		rgb[116] = new Color( 187, 103,   0 );
		rgb[117] = new Color( 188, 104,   0 );
		rgb[118] = new Color( 188, 104,   0 );
		rgb[119] = new Color( 190, 105,   0 );
		rgb[120] = new Color( 191, 107,   0 );
		rgb[121] = new Color( 191, 107,   0 );
		rgb[122] = new Color( 193, 108,   0 );
		rgb[123] = new Color( 193, 108,   0 );
		rgb[124] = new Color( 194, 109,   0 );
		rgb[125] = new Color( 196, 110,   0 );
		rgb[126] = new Color( 196, 110,   0 );
		rgb[127] = new Color( 197, 112,   0 );
		rgb[128] = new Color( 197, 112,   0 );
		rgb[129] = new Color( 199, 113,   0 );
		rgb[130] = new Color( 200, 114,   0 );
		rgb[131] = new Color( 200, 114,   0 );
		rgb[132] = new Color( 202, 116,   0 );
		rgb[133] = new Color( 202, 116,   0 );
		rgb[134] = new Color( 204, 117,   0 );
		rgb[135] = new Color( 205, 118,   0 );
		rgb[136] = new Color( 205, 118,   0 );
		rgb[137] = new Color( 207, 119,   0 );
		rgb[138] = new Color( 208, 121,   0 );
		rgb[139] = new Color( 208, 121,   0 );
		rgb[140] = new Color( 210, 122,   0 );
		rgb[141] = new Color( 211, 123,   0 );
		rgb[142] = new Color( 211, 123,   0 );
		rgb[143] = new Color( 213, 124,   0 );
		rgb[144] = new Color( 214, 126,   0 );
		rgb[145] = new Color( 214, 126,   0 );
		rgb[146] = new Color( 216, 127,   0 );
		rgb[147] = new Color( 217, 128,   0 );
		rgb[148] = new Color( 217, 128,   0 );
		rgb[149] = new Color( 219, 130,   0 );
		rgb[150] = new Color( 221, 131,   0 );
		rgb[151] = new Color( 221, 131,   0 );
		rgb[152] = new Color( 222, 132,   0 );
		rgb[153] = new Color( 224, 133,   0 );
		rgb[154] = new Color( 224, 133,   0 );
		rgb[155] = new Color( 225, 135,   0 );
		rgb[156] = new Color( 227, 136,   0 );
		rgb[157] = new Color( 227, 136,   0 );
		rgb[158] = new Color( 228, 137,   0 );
		rgb[159] = new Color( 230, 138,   0 );
		rgb[160] = new Color( 230, 138,   0 );
		rgb[161] = new Color( 231, 140,   0 );
		rgb[162] = new Color( 233, 141,   0 );
		rgb[163] = new Color( 233, 141,   0 );
		rgb[164] = new Color( 234, 142,   0 );
		rgb[165] = new Color( 236, 144,   0 );
		rgb[166] = new Color( 236, 144,   0 );
		rgb[167] = new Color( 238, 145,   0 );
		rgb[168] = new Color( 239, 146,   0 );
		rgb[169] = new Color( 241, 147,   0 );
		rgb[170] = new Color( 241, 147,   0 );
		rgb[171] = new Color( 242, 149,   0 );
		rgb[172] = new Color( 244, 150,   0 );
		rgb[173] = new Color( 244, 150,   0 );
		rgb[174] = new Color( 245, 151,   0 );
		rgb[175] = new Color( 247, 153,   0 );
		rgb[176] = new Color( 247, 153,   0 );
		rgb[177] = new Color( 248, 154,   0 );
		rgb[178] = new Color( 250, 155,   0 );
		rgb[179] = new Color( 251, 156,   0 );
		rgb[180] = new Color( 251, 156,   0 );
		rgb[181] = new Color( 253, 158,   0 );
		rgb[182] = new Color( 255, 159,   0 );
		rgb[183] = new Color( 255, 159,   0 );
		rgb[184] = new Color( 255, 160,   0 );
		rgb[185] = new Color( 255, 161,   0 );
		rgb[186] = new Color( 255, 163,   0 );
		rgb[187] = new Color( 255, 163,   0 );
		rgb[188] = new Color( 255, 164,   0 );
		rgb[189] = new Color( 255, 165,   0 );
		rgb[190] = new Color( 255, 167,   0 );
		rgb[191] = new Color( 255, 167,   0 );
		rgb[192] = new Color( 255, 168,   0 );
		rgb[193] = new Color( 255, 169,   0 );
		rgb[194] = new Color( 255, 169,   0 );
		rgb[195] = new Color( 255, 170,   0 );
		rgb[196] = new Color( 255, 172,   0 );
		rgb[197] = new Color( 255, 173,   0 );
		rgb[198] = new Color( 255, 173,   0 );
		rgb[199] = new Color( 255, 174,   0 );
		rgb[200] = new Color( 255, 175,   0 );
		rgb[201] = new Color( 255, 177,   0 );
		rgb[202] = new Color( 255, 178,   0 );
		rgb[203] = new Color( 255, 179,   0 );
		rgb[204] = new Color( 255, 181,   0 );
		rgb[205] = new Color( 255, 181,   0 );
		rgb[206] = new Color( 255, 182,   0 );
		rgb[207] = new Color( 255, 183,   0 );
		rgb[208] = new Color( 255, 184,   0 );
		rgb[209] = new Color( 255, 187,   7 );
		rgb[210] = new Color( 255, 188,  10 );
		rgb[211] = new Color( 255, 189,  14 );
		rgb[212] = new Color( 255, 191,  18 );
		rgb[213] = new Color( 255, 192,  21 );
		rgb[214] = new Color( 255, 193,  25 );
		rgb[215] = new Color( 255, 195,  29 );
		rgb[216] = new Color( 255, 197,  36 );
		rgb[217] = new Color( 255, 198,  40 );
		rgb[218] = new Color( 255, 200,  43 );
		rgb[219] = new Color( 255, 202,  51 );
		rgb[220] = new Color( 255, 204,  54 );
		rgb[221] = new Color( 255, 206,  61 );
		rgb[222] = new Color( 255, 207,  65 );
		rgb[223] = new Color( 255, 210,  72 );
		rgb[224] = new Color( 255, 211,  76 );
		rgb[225] = new Color( 255, 214,  83 );
		rgb[226] = new Color( 255, 216,  91 );
		rgb[227] = new Color( 255, 219,  98 );
		rgb[228] = new Color( 255, 221, 105 );
		rgb[229] = new Color( 255, 223, 109 );
		rgb[230] = new Color( 255, 225, 116 );
		rgb[231] = new Color( 255, 228, 123 );
		rgb[232] = new Color( 255, 232, 134 );
		rgb[233] = new Color( 255, 234, 142 );
		rgb[234] = new Color( 255, 237, 149 );
		rgb[235] = new Color( 255, 239, 156 );
		rgb[236] = new Color( 255, 240, 160 );
		rgb[237] = new Color( 255, 243, 167 );
		rgb[238] = new Color( 255, 246, 174 );
		rgb[239] = new Color( 255, 248, 182 );
		rgb[240] = new Color( 255, 249, 185 );
		rgb[241] = new Color( 255, 252, 193 );
		rgb[242] = new Color( 255, 253, 196 );
		rgb[243] = new Color( 255, 255, 204 );
		rgb[244] = new Color( 255, 255, 207 );
		rgb[245] = new Color( 255, 255, 211 );
		rgb[246] = new Color( 255, 255, 218 );
		rgb[247] = new Color( 255, 255, 222 );
		rgb[248] = new Color( 255, 255, 225 );
		rgb[249] = new Color( 255, 255, 229 );
		rgb[250] = new Color( 255, 255, 233 );
		rgb[251] = new Color( 255, 255, 236 );
		rgb[252] = new Color( 255, 255, 240 );
		rgb[253] = new Color( 255, 255, 244 );
		rgb[254] = new Color( 255, 255, 247 );
		rgb[255] = new Color( 255, 255, 255 );
	}
}

