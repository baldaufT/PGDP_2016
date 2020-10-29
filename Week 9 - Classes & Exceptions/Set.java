package hausaufgabe11;

import java.util.Iterator;

public class Set<T> implements Iterable<T> {

	public static void main(String[] args) {
		Set<Integer> s = new Set<Integer>();
		s = s.add(5);
		s = s.add(6);
		Set<Integer> s2 = new Set<Integer>();
		if (!s.equals(s2) && !s2.equals(s))
			System.out.print("nicht reflexiv");
	}

	private final List<T> list;

	public Set() {
		list = new List<T>(null, null);
	}

	private Set(List<T> e) {
		list = e;
	}

	@SuppressWarnings("unchecked")
	public boolean equals(Object o) {
		if (!(o instanceof Set<?>))
			return false;
		if (this.size() != (((Set<T>) o).size()))
			return false;
		List<T> l = list, olist = ((Set<T>) o).list;
		while (l != null) {
			if (!l.equals(olist)) {
				return false;
			}
			l = l.next;
			olist = olist.next;
		}
		return true;
	}

	public String toString() {
		String out = "{";
		List<T> l = list;
		while (l.info != null) {
			out = out + l.info.toString() + ((l.next != null) ? ", " : "");
			l = l.next;
			if (l == null)
				break;
		}
		return out + "}";
	}

	public Set<T> remove(Object o) throws NullPointerException {
		try {
			boolean gefunden = false;
			if (this.size() < 2)
				return new Set<T>();
			if (o.equals(list.info))
				return new Set<T>(list.next);
			List<T> li = list;
			Set<T> set = new Set<T>();
			while (li != null) {
				if (!li.info.equals(o))
					set = set.add(li.info);
				else
					gefunden = true;
				li = li.next;
			}
			return gefunden ? set : new Set<T>(list);
		} catch (NullPointerException excep) {
			throw excep;
		}
	}

	public boolean contains(Object o) {
		if (list == null || list.info == null)
			return false;
		List<T> go = list;
		while (go != null) {
			if (go.info.equals(o))
				return true;
			go = go.next;
		}
		return false;
	}

	public int size() {
		int anzahl = 1;
		if (list == null || list.info == null)
			return 0;
		List<T> go = list;
		while (go.next != null) {
			go = go.next;
			anzahl++;
		}
		return anzahl;
	}

	public Set<T> add(T e) throws NullPointerException {
		if (this.contains(e) || e == null)
			return this;
		Set<T> s;
		try {
			if (list == null || list.info == null) {
				s = new Set<T>(new List<T>(e, null));
			} else
				s = new Set<T>(new List<T>(e, list));
			return s;
		} catch (NullPointerException excep) {
			throw excep;
		}
	}

	@SuppressWarnings("hiding")
	private final class List<T> implements Iterator<T> {
		final T info;
		final List<T> next;
		int zahl = 0;

		public List(T inf, List<T> nex) {
			info = inf;
			next = nex;
		}

		public boolean equals(List<T> o) {
			boolean bool = false;
			if (o.info.equals(this.info))
				bool = true;
			return bool;
		}

		@Override
		public boolean hasNext() {
			List<T> ne = this;
			for (int i = 0; i < zahl; i++) {
				ne = ne.next;
			}
			return ne != null;
		}

		@Override
		public T next() {
			if (this.hasNext()) {
				List<T> ne = this;
				for (int i = 0; i < zahl; i++) {
					ne = ne.next;
				}
				zahl++;
				return ne.info;
			}
			return null;
		}
	}

	@Override
	public Iterator<T> iterator() {
		return list;
	}

}
