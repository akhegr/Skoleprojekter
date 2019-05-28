﻿using System.Collections.Generic;
using System.Threading.Tasks;
using MobilSemProjekt.MVVM.Model;

namespace MobilSemProjekt.MVVM.Service
{
    public interface IRatingRestService
    {
        Task Create(Rating rating);
        Task<double> GetAverageRating(Location location);
        
        void Update(Rating rating);
        Task<bool> Delete(Rating rating);
    }
}