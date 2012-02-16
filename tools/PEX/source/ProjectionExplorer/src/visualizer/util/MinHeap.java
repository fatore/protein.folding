/* ***** BEGIN LICENSE BLOCK *****
 *
 * Copyright (c) 2005-2007 Universidade de Sao Paulo, Sao Carlos/SP, Brazil.
 * All Rights Reserved.
 *
 * This file is part of Projection Explorer (PEx), based on the code presented 
 * in:
 * 
 * http://snippets.dzone.com/user/scvalex/tag/prim
 *
 * How to cite this work:
 *  
@inproceedings{paulovich2007pex,
author = {Fernando V. Paulovich and Maria Cristina F. Oliveira and Rosane 
Minghim},
title = {The Projection Explorer: A Flexible Tool for Projection-based 
Multidimensional Visualization},
booktitle = {SIBGRAPI '07: Proceedings of the XX Brazilian Symposium on 
Computer Graphics and Image Processing (SIBGRAPI 2007)},
year = {2007},
isbn = {0-7695-2996-8},
pages = {27--34},
doi = {http://dx.doi.org/10.1109/SIBGRAPI.2007.39},
publisher = {IEEE Computer Society},
address = {Washington, DC, USA},
}
 *  
 * PEx is free software: you can redistribute it and/or modify it under 
 * the terms of the GNU General Public License as published by the Free 
 * Software Foundation, either version 3 of the License, or (at your option) 
 * any later version.
 *
 * PEx is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details.
 *
 * This code was developed by members of Computer Graphics and Image
 * Processing Group (http://www.lcad.icmc.usp.br) at Instituto de Ciencias
 * Matematicas e de Computacao - ICMC - (http://www.icmc.usp.br) of 
 * Universidade de Sao Paulo, Sao Carlos/SP, Brazil. The initial developer 
 * of the original code is Fernando Vieira Paulovich <fpaulovich@gmail.com>.
 *
 * Contributor(s): Rosane Minghim <rminghim@icmc.usp.br>
 *
 * You should have received a copy of the GNU General Public License along 
 * with PEx. If not, see <http://www.gnu.org/licenses/>.
 *
 * ***** END LICENSE BLOCK ***** */

package visualizer.util;

import java.io.IOException;

/**
 * Binary heap routines - root (at subscript 1) has minimum. 
 * Also supports handle array for linking from another data structure.
 * handle[i] gives the heap subscript for the entry with
 * id number i.  (Also, handle[heap[j].id]==j . . .).
 */
public class MinHeap {

    public MinHeap() {
        new MinHeap(50);
    }

    public MinHeap(int capacity) {
        heap = new HeapEntry[capacity + 1];
        handle = new int[capacity];

        /*
        for (int i=0;i<capacity+1;i++)
        heap[i]=new HeapEntry();
         */

        // Invalidate handles
        for (int i = 0; i < capacity; i++) {
            handle[i] = (-1);
        }
        maxSub = capacity + 1;
        n = 0;
    }

    public MinHeap(int capacity, float[] priorityIn, int count) // Constructs a heap using the provided priorities.
    // priorityIn[i] will have id i in the constructed heap,
    // so handle[i] may be used to track it.
    {
        int i;

        heap = new HeapEntry[capacity + 1];
        handle = new int[capacity];

        /*
        for (i=0;i<capacity+1;i++)
        heap[i]=new HeapEntry();
         */

        maxSub = capacity + 1;
        n = count;
        // Caller should know conventions for ids and handles.
        for (i = 0; i < count; i++) {
            heap[i + 1] = new HeapEntry(); // Create entries as needed
            heap[i + 1].priority = priorityIn[i];
            heap[i + 1].id = i;
            handle[i] = i + 1;
        }
        // Invalidate unused handles
        for (; i < capacity; i++) {
            handle[i] = (-1);        // Sliding down starting from each node that is a parent,
        // in descending subscript (not priority) order.
        }
        for (i = parent(n); i > 0; i--) {
            heapify(i);
        }
    }

    public int left(int x) // Left child of x
    {
        return 2 * x;
    }

    public int right(int x) // Right child of x
    {
        return 2 * x + 1;
    }

    public int parent(int x) // Parent of x
    {
        return x / 2;
    }

    public boolean full() // Is this heap full?
    {
        return n == maxSub;
    }

    public boolean empty() // Is this heap empty?
    {
        return n == 0;
    }

    public void heapify(int j) {
        // The subtrees to the left and right of heap[j] must have
        // the MinHeap property.  At completion of this routine, the
        // entire subtree rooted at heap[j] will have the MinHeap
        // property.
        int best, r;
        HeapEntry entry = heap[j];

        while (true) {
            // Find child with smaller priority
            best = left(j);
            if (best > n) {
                break;  // Gone past the leaves
            }
            r = right(j);
            // Does left or right child have the smaller priority?
            if (r <= n && heap[best].priority > heap[r].priority) {
                best = r;
            }
            if (entry.priority <= heap[best].priority) {
                break;  // MinHeap property holds in subtree

            // Move best child to parent
            }
            heap[j] = heap[best];
            handle[heap[j].id] = j;
            // Continue rippling one level deeper.
            // Again, the left and right subtrees have the MinHeap property.
            j = best;
        }
        // Drop off item from root of subtree (original j)
        heap[j] = entry;
        handle[heap[j].id] = j;
    }

    public void swapUp(int j) {
        // Ancestors of heap[j] have the MinHeap property,
        // so fixing the heap is a matter of having heap[j]
        // climb past its ancestors with larger priorities.
        int p;
        HeapEntry entry = heap[j];

        p = parent(j);
        while (p >= 1 && heap[p].priority > entry.priority) {
            heap[j] = heap[p];
            handle[heap[j].id] = j;
            j = p;
            p = parent(j);
        }

        // Drop off item with new priority
        heap[j] = entry;
        handle[heap[j].id] = j;
    }

    public float getPriority(int id) throws IOException {
        if (id < 0 || id >= maxSub || handle[id] == (-1)) {
            throw new IOException("id " + id + " issue for getPriority");
        }
        return heap[handle[id]].priority;
    }

    public void changePriority(HeapEntry iEntry) {
        // Changes priority of item iId to iPriority
        int i = handle[iEntry.id];

        if (iEntry.priority > heap[i].priority) {
            // New priority is larger, so slide down
            heap[i].priority = iEntry.priority;
            heapify(i);
        } else {
            // New priority is smaller, so swap up
            heap[i].priority = iEntry.priority;
            swapUp(i);
        }
    }

    public void insert(HeapEntry iEntry) throws IOException {
        // Insert a new heap entry. 
        int j;

        if (full()) {
            throw new IOException("Heap table size exceeded!");
        }
        if (iEntry.id < 0 || iEntry.id >= maxSub || handle[iEntry.id] != (-1)) {
            throw new IOException("id " + iEntry.id + " issue for insert");
        }
        j = (++n);  // Put next item into use

        // Swap the new item up the heap
        heap[j] = new HeapEntry();  // Could implement Cloneable
        heap[j].priority = iEntry.priority;
        heap[j].id = iEntry.id;
        swapUp(j);
    }

    public void deleteId(int id) throws IOException // Deletes the entry for id and frees its handle.
    {
        HeapEntry work;
        int heapSlot, donor;

        if (id < 0 || id >= maxSub || handle[id] == (-1)) {
            throw new IOException("id " + id + " issue for delete");
        }
        heapSlot = handle[id];
        handle[id] = (-1);
        donor = (n--);         // Last leaf element gets moved
        if (donor == heapSlot) {
            return;        // Next two lines transform the donation into a change of priority.
        }
        heap[heapSlot].id = heap[donor].id;
        handle[heap[heapSlot].id] = heapSlot;
        changePriority(heap[donor]);
    }

    public HeapEntry minimum() throws IOException // Returns minimum element, but leaves it in the heap.
    {
        if (empty()) {
            throw new IOException("Heap empty!");
        }
        HeapEntry work = new HeapEntry();  // Could implement Cloneable
        work.priority = heap[1].priority;
        work.id = heap[1].id;
        return work;
    }

    public HeapEntry extractMin() throws IOException {
        HeapEntry returnEntry;

        if (empty()) {
            throw new IOException("Heap empty!");        // Values being returned
        }
        returnEntry = heap[1];
        handle[heap[1].id] = (-1); // Invalidate handle

        n--;  // Item is taken out of use
        if (empty()) {
            return returnEntry;        // Last item (at highest-subscripted leaf) becomes the root and slides down.
        }
        heap[1] = heap[n + 1];
        heapify(1);
        return returnEntry;
    }

    public static class HeapEntry {

        public float priority;
        public int id; // Indicates which item the priority applies to
    }

    private int maxSub;        // Maximum number of elements that heap may hold.
    private int n;             // Actual entries in the heap.
    private HeapEntry[] heap;  // Element 0 is not used
    private int[] handle;      // Subscripts must be in the range 0..maxSub-1
}
