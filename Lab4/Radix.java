
public class Radix {

	Queue temp = new Queue();
	Queue p = new Queue();
	Queue q = new Queue();
	Queue w = new Queue();
	Queue e = new Queue();
	Queue r = new Queue();
	Queue t = new Queue();
	Queue y = new Queue();
	Queue u = new Queue();
	Queue i = new Queue();
	Queue o = new Queue();
	Queue sorted = new Queue();
	
	
	public Queue sort(Queue inq) throws QueueEmptyException {
		
		while(!inq.isEmpty()) {
			int j = inq.remove();
				if (numLen(j) == 1) {
				position1(j);
			}
			else {
				position(j);
			}
		}
		compile();
		while(!temp.isEmpty()) {
			int j = temp.remove();
			if (numLen(j) == 1) {
				p.add(j);
			}
			else {
				position2(j);
			}
		}
		compile();
		while(!temp.isEmpty()) {
			int j = temp.remove();
			if (numLen(j) == 3) {
				position3(j);
			}
			else {
				p.add(j);
			}
		}
		compileFinal();
		return sorted;
	}
	
	public void compileFinal() throws QueueEmptyException {
		while(!p.isEmpty()) {
			sorted.add(p.remove());
		}
		while(!q.isEmpty()) {
			sorted.add(q.remove());
		}
		while(!w.isEmpty()) {
			sorted.add(w.remove());
		}
		while(!e.isEmpty()) {
			sorted.add(e.remove());
		}
		while(!r.isEmpty()) {
			sorted.add(r.remove());
		}
		while(!t.isEmpty()) {
			sorted.add(t.remove());
		}
		while(!y.isEmpty()) {
			sorted.add(y.remove());
		}
		while(!u.isEmpty()) {
			sorted.add(u.remove());
		}
		while(!i.isEmpty()) {
			sorted.add(i.remove());
		}
		while(!o.isEmpty()) {
			sorted.add(o.remove());
		}
	}
	
	public void compile() throws QueueEmptyException {
		while(!p.isEmpty()) {
			temp.add(p.remove());
		}
		while(!q.isEmpty()) {
			temp.add(q.remove());
		}
		while(!w.isEmpty()) {
			temp.add(w.remove());
		}
		while(!e.isEmpty()) {
			temp.add(e.remove());
		}
		while(!r.isEmpty()) {
			temp.add(r.remove());
		}
		while(!t.isEmpty()) {
			temp.add(t.remove());
		}
		while(!y.isEmpty()) {
			temp.add(y.remove());
		}
		while(!u.isEmpty()) {
			temp.add(u.remove());
		}
		while(!i.isEmpty()) {
			temp.add(i.remove());
		}
		while(!o.isEmpty()) {
			temp.add(o.remove());
		}
	}
	
	public void position(int j) {
		if (j%10 == 0) {
			p.add(j);
		}
		else if (j%10 == 1) {
			q.add(j);
		}
		else if (j%10 == 2) {
			w.add(j);
		}
		else if (j%10 == 3) {
			e.add(j);
		}
		else if (j%10 == 4) {
			r.add(j);
		}
		else if (j%10 == 5) {
			t.add(j);
		}
		else if (j%10 == 6) {
			y.add(j);
		}
		else if (j%10 == 7) {
			u.add(j);
		}
		else if (j%10 == 8) {
			i.add(j);
		}
		else if (j%10 == 9) {
			o.add(j);
		}
	}

	public void position1(int j) {
		if (j == 0) {
			p.add(j);
		}
		else if (j == 1) {
			q.add(j);
		}
		else if (j == 2) {
			w.add(j);
		}
		else if (j == 3) {
			e.add(j);
		}
		else if (j == 4) {
			r.add(j);
		}
		else if (j == 5) {
			t.add(j);
		}
		else if (j == 6) {
			y.add(j);
		}
		else if (j == 7) {
			u.add(j);
		}
		else if (j == 8) {
			i.add(j);
		}
		else if (j == 9) {
			o.add(j);
		}
	}
	
	public void position2(int j) {
		if ((j%100)/10 == 0) {
			p.add(j);
		}
		else if ((j%100)/10 == 1) {
			q.add(j);
		}
		else if ((j%100)/10 == 2) {
			w.add(j);
		}
		else if ((j%100)/10 == 3) {
			e.add(j);
		}
		else if ((j%100)/10 == 4) {
			r.add(j);
		}
		else if ((j%100)/10 == 5) {
			t.add(j);
		}
		else if ((j%100)/10 == 6) {
			y.add(j);
		}
		else if ((j%100)/10 == 7) {
			u.add(j);
		}
		else if ((j%100)/10 == 8) {
			i.add(j);
		}
		else if ((j%100)/10 == 9) {
			o.add(j);
		}
	}
	
	public void position3(int j) {
		int f = j / 100;
		if ((f%10) == 0) {
			p.add(j);
		}
		else if ((f%10) == 1) {
			q.add(j);
		}
		else if ((f%10) == 2) {
			w.add(j);
		}
		else if ((f%10) == 3) {
			e.add(j);
		}
		else if ((f%10) == 4) {
			r.add(j);
		}
		else if ((f%10) == 5) {
			t.add(j);
		}
		else if ((f%10) == 6) {
			y.add(j);
		}
		else if ((f%10) == 7) {
			u.add(j);
		}
		else if ((f%10) == 8) {
			i.add(j);
		}
		else if ((f%10) == 9) {
			o.add(j);
		}
	}
	
	public int numLen(int num) {
		return String.valueOf(num).length();
	}
	
}