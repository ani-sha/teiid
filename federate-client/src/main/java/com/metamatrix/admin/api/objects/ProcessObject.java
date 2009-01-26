/*
 * JBoss, Home of Professional Open Source.
 * Copyright (C) 2008 Red Hat, Inc.
 * Copyright (C) 2000-2007 MetaMatrix, Inc.
 * Licensed to Red Hat, Inc. under one or more contributor 
 * license agreements.  See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 */

package com.metamatrix.admin.api.objects;

import java.net.InetAddress;



/** 
 * A Process in the MetaMatrix system.
 * 
 * <p>The identifier pattern for a Process is <code>"hostName.processName"</code>.
 * This Process identifier is concidered to be unique across the system.</p>
 * @since 4.3
 */
public interface ProcessObject extends
                        AdminObject {
    
    /**
     * Proces Min Heap Size Property Name
     */
    public static final String VM_MINIMUM_HEAP_SIZE_PROPERTY_NAME = "vm.starter.minHeapSize"; //$NON-NLS-1$  
    /**
     * Proces Max Heap Size Property Name
     */
    public static final String VM_MAXIMUM_HEAP_SIZE_PROPERTY_NAME = "vm.starter.maxHeapSize"; //$NON-NLS-1$
    /**
     * Proces Port Property Name
     */
    public static final String SERVER_PORT = "vm.socketPort"; //$NON-NLS-1$
    /**
     * Proces Max Treads Property Name
     */
    public static final String MAX_THREADS = "vm.maxThreads"; //$NON-NLS-1$
    /**
     * Proces Time To Live Property Name
     */
    public static final String TIMETOLIVE = "vm.timetolive"; //$NON-NLS-1$
    /**
     * Proces Import Buffer Size Property Name
     */
    public static final String INPUT_BUFFER_SIZE = "vm.inputBufferSize";       //$NON-NLS-1$
    /**
     * Proces Output Buffer Size Property Name
     */
    public static final String OUTPUT_BUFFER_SIZE = "vm.outputBufferSize";       //$NON-NLS-1$ 
    /**
     * Proces Forced Shutdown Time Property Name
     */
    public static final String FORCED_SHUTDOWN_TIME = "vm.forced.shutdown.time"; //$NON-NLS-1$
    /**
     * Proces Enabled Flag Property Name
     */
    public static final String ENABLED_FLAG = "vm.enabled"; //$NON-NLS-1$

    /**
     * Get the {@link Host} Identifier for this MetaMatrix Process
     *  
     * @return String A unique identifier for the Host of this Process.
     * @since 4.3
     */
    public String getHostIdentifier();


    /**
     * Get the port number for this MetaMatrix Process
     *  
     * @return listener port for this host
     * @since 4.3
     */
    public int getPort();
    
    /**
     * Get the IP address for the MetaMatrix Process 
     * @return the IP address for the MetaMatrix Process 
     * @since 4.3
     */
    public InetAddress getInetAddress(); 
       
    /**
     * Is this process enabled in Configuration
     *  
     * @return whether this process is enabled.
     * @since 4.3
     */
    public boolean isEnabled();

    /**
     * @return amount of free memory for this Java process.
     */
    public long getFreeMemory();    
    
    
    /**
     * @return thread count for this Java process.
     */
    public int getThreadCount();

    /**
     * @return total memory allocated for this Java process.
     */
    public long getTotalMemory();

    
    /**
     * @return whether this process is running.
     * @since 4.3
     */
    public boolean isRunning();
    
    
    
    
    /** 
     * @return Returns the maxSockets.
     * @since 4.3
     */
    public int getMaxSockets();
    /** 
     * @return Returns the maxVirtualSockets.
     * @since 4.3
     */
    public int getMaxVirtualSockets();
    /** 
     * @return Returns the objectsRead.
     * @since 4.3
     */
    public long getObjectsRead();
    /** 
     * @return Returns the objectsWritten.
     * @since 4.3
     */
    public long getObjectsWritten();
    /** 
     * @return Returns the sockets.
     * @since 4.3
     */
    public int getSockets();
    /** 
     * @return Returns the virtualSockets.
     * @since 4.3
     */
    public int getVirtualSockets();
    
    
    /** 
     * @return Returns the queueWorkerPool.
     * @since 4.3
     */
    public QueueWorkerPool getQueueWorkerPool();
}
