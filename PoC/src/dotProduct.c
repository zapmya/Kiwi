void print(long x);

void printSIMD(int x[16]);

int main(char** argv, int argc) {

    long cnt = 0;

	int x[16] = {0,1,2,3 ,4,5,6,7, 8,9,10,11, 12,13,14,15};
	int y[16] = {7,8,9,6, 10,11,12,14, 15,16,17,18, 19,20,21,22};

	while(1) {
		for ( int i = 0; i < 16; i++ ) {
			x[i] = x[i]*y[i];
		}

		cnt = cnt+1;
		if ( (cnt % (1000*1000)) == 0 ) {
			print(cnt);
			printSIMD(x);
		}
	}
}
