package com.github.hatimiti.doxer.common.domain.type;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.function.Predicate;

public abstract class ListType<O, E extends List<O>>
		extends Type<E>
		implements List<O> {

	protected final E list;
	
	public ListType(E list) {
		this.list = list;
	}
	
	@Override
	public E getVal() {
		return this.list;
	}

	@Override
	public int size() {
		return this.list.size();
	}

	@Override
	public boolean isEmpty() {
		return this.list.isEmpty();
	}

	@Override
	public boolean contains(final Object paramObject) {
		return this.list.contains(paramObject);
	}

	@Override
	public Iterator<O> iterator() {
		return this.list.iterator();
	}

	@Override
	public Object[] toArray() {
		return this.list.toArray();
	}

	@Override
	public <T> T[] toArray(final T[] paramArrayOfT) {
		return this.list.toArray(paramArrayOfT);
	}

	@Override
	public boolean add(final O entity) {
		return this.list.add(entity);
	}

	@Override
	public O remove(final int index) {
		return this.list.remove(index);
	}

	@Override
	public boolean remove(final Object paramObject) {
		return this.list.remove(paramObject);
	}

	@Override
	public boolean containsAll(final Collection<?> paramCollection) {
		return this.list.containsAll(paramCollection);
	}

	@Override
	public boolean addAll(final Collection<? extends O> paramCollection) {
		return this.list.addAll(paramCollection);
	}

	@Override
	public boolean addAll(final int paramInt,
			final Collection<? extends O> paramCollection) {
		return this.list.addAll(paramCollection);
	}

	@Override
	public boolean removeAll(final Collection<?> paramCollection) {
		return this.list.removeAll(paramCollection);
	}

	@Override
	public boolean retainAll(final Collection<?> paramCollection) {
		return this.list.retainAll(paramCollection);
	}

	@Override
	public void clear() {
		this.list.clear();
	}

	@Override
	public O get(final int paramInt) {
		return this.list.get(paramInt);
	}

	@Override
	public O set(final int paramInt, final O paramE) {
		return this.list.set(paramInt, paramE);
	}

	@Override
	public void add(final int paramInt, final O paramE) {
		this.list.add(paramInt, paramE);
	}

	@Override
	public int indexOf(final Object paramObject) {
		return this.list.indexOf(paramObject);
	}

	@Override
	public int lastIndexOf(final Object paramObject) {
		return this.list.lastIndexOf(paramObject);
	}

	@Override
	public ListIterator<O> listIterator() {
		return this.list.listIterator();
	}

	@Override
	public ListIterator<O> listIterator(final int paramInt) {
		return this.list.listIterator(paramInt);
	}

	@Override
	public List<O> subList(final int paramInt1, final int paramInt2) {
		return this.list.subList(paramInt1, paramInt2);
	}

	protected boolean removeAllIf(Predicate<O> filter) {
		Objects.requireNonNull(filter);
		boolean r = false;
		for (int i = size() - 1; 0 <= i; i--) {
			if (!filter.test(getVal().get(i))) {
				continue;
			}
			this.list.remove(i);
			r |= true;
		}
		return r;
	}
}
